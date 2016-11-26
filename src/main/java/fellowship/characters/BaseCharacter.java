package fellowship.characters;

import com.nmerrill.kothcomm.game.maps.Point2D;
import com.nmerrill.kothcomm.game.maps.graphmaps.GraphMap;
import com.nmerrill.kothcomm.game.maps.graphmaps.neighborhoods.VonNeumannNeighborhood;
import com.nmerrill.kothcomm.utils.ActionQueue;
import com.nmerrill.kothcomm.utils.Cache;
import com.nmerrill.kothcomm.utils.Event;
import com.nmerrill.kothcomm.utils.EventManager;
import fellowship.*;
import fellowship.abilities.Ability;
import fellowship.actions.Action;
import fellowship.actions.ReadonlyAction;
import fellowship.actions.attacking.Slice;
import fellowship.actions.mobility.Step;
import fellowship.actions.other.Smile;
import fellowship.actions.other.Wall;
import fellowship.events.*;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.tuple.Tuples;

import java.util.Collection;
import java.util.Random;
import java.util.function.Function;

import static com.nmerrill.kothcomm.game.maps.graphmaps.GraphMap.getNeighbors;

public class BaseCharacter implements MapObject {

    /**
     * We can use a static cache, because the map size never changes
     */
    private final static MutableMap<Pair<Point2D, Range>, Cache<MutableSet<Point2D>>> cached = Maps.mutable.empty();

    public final static double MANA_REGEN_PER_INT = .1;
    public final static double HEALTH_REGEN_PER_STR = .5;
    public final static int MANA_PER_INT = 7;
    public final static int HEALTH_PER_STR = 10;
    public final static int DELAY_PER_AGI = 1;
    private final ReadonlyCharacter readonly;
    private final EnemyCharacter enemy;
    private final Team team;
    private final GraphMap<Point2D, MapObject> map;
    private final ActionQueue actionQueue;
    private final Random random;
    private final MutableList<Ability> abilities;
    private final MutableList<Action> actions;
    private final Action slice, step, smile;
    private final MutableIntList stats;
    private Stat primary;
    private int smartness, cleverness;
    private double health, mana;
    private double manaRegen, healthRegen;
    private int maxHealth, maxMana;
    private int delay;
    private int stunTick;
    private int poisonTurn, poisonAmount;
    private int frozenTurn;
    private int silenceTurn;
    private int invisibleTurn;
    private int turn;
    private boolean dead;
    private Range sightRange, sliceRange, stepRange;
    private Action lastAction;
    private EventManager<Events> eventManager;
    private Point2D currentLocation;

    public BaseCharacter(ActionQueue actionQueue, GraphMap<Point2D, MapObject> map, Team team, Random random){
        abilities = Lists.mutable.empty();
        actions = Lists.mutable.empty();
        stats = IntLists.mutable.of(0,0,0);
        this.map = map;
        this.team = team;
        this.random = random;
        this.sightRange = new Range(2);
        this.sliceRange = new Range(1, true);
        this.stepRange = new Range(1, true);
        this.delay = 100;
        this.actionQueue = actionQueue;
        this.eventManager = new EventManager<>();
        this.slice = new Slice(this);
        this.step = new Step(this);
        this.smile = new Smile(this);
        this.actions.add(slice);
        this.actions.add(step);
        this.actions.add(smile);
        this.manaRegen = 0;
        this.healthRegen = 0;
        this.silenceTurn = -1;
        this.poisonTurn = -1;
        this.frozenTurn = -1;
        this.invisibleTurn = -1;
        this.readonly = new ReadonlyCharacter(this);
        this.enemy = new EnemyCharacter(this);
        team.addCharacter(this);
    }

    public Random getRandom() {
        return random;
    }

    public ActionQueue getActionQueue() {
        return actionQueue;
    }

    public void start(){
        primary = Stat.values()[stats.indexOf(stats.max())];
        actionQueue.enqueue(this::action, delay);
    }

    public void setSmartness(int smartness){
        this.smartness = smartness;
    }

    public int smartness(){
        return smartness;
    }

    public void setCleverness(int cleverness){
        this.cleverness = cleverness;
    }

    public int cleverness(){
        return cleverness;
    }

    public int getStat(Stat stat){
        return stats.get(stat.ordinal());
    }

    public void addAction(Action action){
        actions.add(action);
    }

    public void removeAction(Action action){
        actions.remove(action);
    }

    public MutableList<Action> getActions() {
        return actions;
    }

    public Range getSightRange(){
        return sightRange;
    }

    public void setSightRange(Range range){
        this.sightRange = range;
    }

    public void addStat(Stat stat, int amount){
        stats.set(stat.ordinal(), stats.get(stat.ordinal())+amount);
        switch (stat){
            case STR:
                maxHealth += HEALTH_PER_STR*amount;
                heal(HEALTH_PER_STR*amount);
                healthRegen += HEALTH_REGEN_PER_STR*amount;
            break;
            case AGI:
                delay -= DELAY_PER_AGI*amount;
            break;
            case INT:
                maxMana += MANA_PER_INT*amount;
                addMana(MANA_PER_INT*amount);
                manaRegen += MANA_REGEN_PER_INT*amount;
            break;
        }
    }

    public void addManaRegen(double amount){
        manaRegen += amount;
    }

    public void addHealthRegen(double amount){
        healthRegen += amount;
    }

    public double getManaRegen(){
        return manaRegen;
    }

    public double getHealthRegen(){
        return healthRegen;
    }

    public boolean isStunned(){
        return stunTick > actionQueue.getTime();
    }

    public boolean isPoisoned(){
        return poisonTurn > turn;
    }

    public int getPoisonAmount(){
        return isPoisoned()?poisonAmount:0;
    }

    public boolean isSilenced(){
        return silenceTurn > turn;
    }

    public boolean isInvisible(){
        return invisibleTurn > turn;
    }

    public boolean isDead(){
        return dead;
    }

    public void addAbility(Ability ability){
        abilities.add(ability);
        ability.apply(this);
    }

    public Action getLastAction(){
        return lastAction;
    }

    public void slow(int amount){
        delay += amount;
    }

    public void heal(double amount){
        health += amount;
        if (health > maxHealth){
            health = maxHealth;
        }
    }

    public void invisible(int duration){
        invisibleTurn = Math.max(turn + duration, invisibleTurn);
    }

    public void reveal(){
        invisibleTurn = -1;
    }

    public int action(){
        if (isDead()){
            return -1;
        }
        turn++;
        Player player = team.getPlayer();
        TurnStartEvent event = new TurnStartEvent();
        eventManager.addEvent(event, Events.TurnStart);
        if (event.isCancelled()){
            return delay;
        }
        heal(healthRegen);
        addMana(manaRegen);
        if (isPoisoned()){
            damage(getPoisonAmount());
            if (isDead()){
                return -1;
            }
        }

        MutableSet<Action> actions = this.actions.select(Action::isAvailable).tap(Action::clearSelection).toSet();
        MutableMap<ReadonlyAction, Action> readonlyActions = actions.toMap(Action::readonly, i->i);
        player.setVisibleEnemies(visibleEnemies().toMap(BaseCharacter::getLocation, BaseCharacter::enemy));
        ReadonlyAction choice = player.choose(readonlyActions.keysView().toSet(), readonly);
        if (!readonlyActions.containsKey(choice)){
            System.out.println(player.getName()+" returned "+(choice==null?"a null":"an invalid")+" action.  Performing Smile");
            choice = smile.readonly();
        }
        Action action = readonlyActions.get(choice);

        if (action.breaksInvisibility()){
            invisibleTurn = -1;
        }
        action.act();
        lastAction = action;
        if (isDead()){
            return -1;
        }
        eventManager.addEvent(new TurnEndEvent(), Events.TurnEnd);
        return delay;
    }

    public void step(Point2D location){
        if (isFrozen()){
            return;
        }
        StepEvent event = new StepEvent(this, location);
        eventManager.addEvent(event, Events.Step);
        if (event.isCancelled()){
            return;
        }
        GraphMap<Point2D, MapObject> map = getMap();
        if (map.contains(location)){
            ((TrapStack)map.get(location)).onStep(this);
            map.clear(location);
        }
        setLocation(location);
    }

    public boolean isFrozen(){
        return frozenTurn >= turn;
    }

    public void stun(int duration){
        stunTick = actionQueue.getTime() + duration;
    }

    public void dispel(){
        frozenTurn = 0;
        invisibleTurn = 0;
        stunTick = 0;
        poisonTurn = 0;
        silenceTurn = 0;
    }

    public void silence(int duration){
        silenceTurn = turn + duration;
    }

    public void poison(int amount, int duration){
        if (isPoisoned()){
            amount += poisonAmount;
        }
        poisonTurn = turn + duration;
        poisonAmount = amount;
    }

    public int on(Events event, Function<Event, Boolean> listener){
        return eventManager.addListener(event, listener);
    }

    public void off(int id){
        eventManager.removeListener(id);
    }

    public void freeze(int turns){
        frozenTurn = turn + turns;
    }

    public void slice(BaseCharacter character){
        SliceEvent event = new SliceEvent(this, character, stats.get(primary.ordinal()));
        eventManager.addEvent(event, Events.Slice);
        character.eventManager.addEvent(event, Events.Sliced);
        if (!event.isCancelled()) {
            character.damage(this, event.getAmount());
        }
    }

    public Stat primaryStat(){
        return primary;
    }

    public MutableSet<Point2D> rangeAround(Range range){
        return rangeAround(range, currentLocation, map);
    }

    public static MutableSet<Point2D> rangeAround(Range range, Point2D location, GraphMap<Point2D, MapObject> map){
        return cached.getIfAbsentPut(Tuples.pair(location, range), Cache::new).get(() -> {
            MutableSet<Point2D> points = Sets.mutable.empty();
            points.add(location);
            if (range.isCardinal()) {
                for (int i = 1; i <= range.getRange(); i++) {
                    points.add(location.moveX(i));
                    points.add(location.moveY(i));
                    points.add(location.moveX(-i));
                    points.add(location.moveY(-i));
                }
            } else {
                VonNeumannNeighborhood neighborhood = new VonNeumannNeighborhood();
                points.addAll(getNeighbors(location, range.getRange(),
                        new org.eclipse.collections.api.block.function.Function<Point2D, Collection<Point2D>>() {
                    private boolean firstStep = true;
                    @Override
                    public Collection<Point2D> valueOf(Point2D each) {
                        if (firstStep){
                            firstStep = false;
                            return map.getNeighbors(each);
                        } else {
                            return neighborhood.getAdjacencies(each).select(map::inBounds);
                        }
                    }
                }));
            }
            return points.select(map::inBounds);
        });
    }

    public MutableSet<BaseCharacter> teammates(Range range){
        MutableSet<Point2D> points = rangeAround(range);
        return teammates().select(t -> points.contains(t.currentLocation));
    }

    public MutableSet<BaseCharacter> teammates(int range){
        return teammates(new Range(range));
    }

    public MutableSet<BaseCharacter> teammates(){
        return team.getCharacters().toSet().without(this);
    }

    public MutableSet<BaseCharacter> visibleEnemies(int range){
        return visibleEnemies(new Range(range));
    }

    public MutableSet<BaseCharacter> visibleEnemies(Range range){
        MutableSet<BaseCharacter> enemies = team.getEnemyTeam().getCharacters().toSet();
        MutableSet<Point2D> teamVision = teamVision();
        MutableSet<Point2D> withinRange = rangeAround(range);
        return enemies.reject(BaseCharacter::isInvisible)
                .select(t ->
                        withinRange.contains(t.currentLocation) &&
                        teamVision.contains(t.currentLocation)
                );
    }

    private MutableSet<Point2D> teamVision(){
        return team.getCharacters().flatCollect(c -> c.rangeAround(c.getSightRange()), Sets.mutable.empty());
    }

    public MutableSet<BaseCharacter> visibleEnemies(){
        return visibleEnemies(1000);
    }

    public MutableMap<Point2D, MapObject> visibleStructures() {
        return teamVision()
                .select(map::contains)
                .select(p -> (map.get(p) instanceof TrapStack) || (map.get(p) instanceof Wall))
                    .toMap(i -> i, map::get);
    }

    public void damage(BaseCharacter source, double amount){
        DamageEvent damageEvent = new DamageEvent(source, this, amount);
        eventManager.addEvent(damageEvent, Events.Damaged);
        if (!damageEvent.isCancelled()) {
            damage(damageEvent.getAmount());
        }
    }

    public void damage(double amount){
        this.health -= amount;
        if (this.health <= 0){
            die();
        }
    }

    public void die(){
        if (dead){
            return;
        }
        DeathEvent event = new DeathEvent();
        eventManager.addEvent(event, Events.Death);
        if (event.isCancelled()){
            return;
        }
        this.dead = true;
        this.team.removeCharacter(this);
        this.map.clear(currentLocation);
        currentLocation = null;
    }

    public void setLocation(Point2D location){
        if (map.inBounds(location) && getMap().isEmpty(location)) {
            if (currentLocation == null){
                map.put(location, this);
            } else {
                map.move(currentLocation, location);
            }
            currentLocation = location;
        }
    }

    public Point2D getLocation(){
        return currentLocation;
    }

    public Range getStepRange(){
        return stepRange;
    }

    public void setStepRange(Range stepRange) {
        this.stepRange = stepRange;
    }

    public Range getSliceRange() {
        return sliceRange;
    }

    public void setSliceRange(Range sliceRange) {
        this.sliceRange = sliceRange;
    }

    public double getMana() {
        return mana;
    }

    public void removeMana(double amount){
        this.mana = Math.max(this.mana-amount, 0);
    }

    public void addMana(double amount){
        this.mana = Math.min(this.mana + amount, maxMana);
    }

    public int getMaxMana(){
        return maxMana;
    }

    public double getHealth(){
        return health;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int max){
        this.maxHealth = max;
    }

    public MutableList<Ability> getAbilities(){
        return abilities.clone();
    }

    public Team getTeam() {
        return team;
    }

    public GraphMap<Point2D, MapObject> getMap(){
        return map;
    }

    public ReadonlyCharacter readonly(){
        return readonly;
    }

    public EnemyCharacter enemy(){
        return enemy;
    }

    @Override
    public String toString() {
        return getTeam().getPlayer().getName()+" Character";
    }
}
