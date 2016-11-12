package fellowship.characters;

import com.nmerrill.kothcomm.game.maps.Point2D;
import com.nmerrill.kothcomm.game.maps.graphmaps.GraphMap;
import com.nmerrill.kothcomm.utils.ActionQueue;
import com.nmerrill.kothcomm.utils.Cache;
import com.nmerrill.kothcomm.utils.EventManager;
import fellowship.MapObject;
import fellowship.Player;
import fellowship.Range;
import fellowship.Stat;
import fellowship.abilities.Ability;
import fellowship.actions.Action;
import fellowship.actions.ReadonlyAction;
import fellowship.actions.attacking.Slice;
import fellowship.actions.mobility.Step;
import fellowship.actions.other.Smile;
import fellowship.events.*;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.util.Random;
import java.util.function.Function;

public class BaseCharacter implements MapObject {

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
    private final MutableMap<Range, Cache<MutableSet<Point2D>>> cached;
    private Stat primary;
    private int smartness, cleverness;
    private double health, mana;
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
    private EventManager eventManager;
    private Point2D currentLocation;

    public BaseCharacter(ActionQueue actionQueue, GraphMap<Point2D, MapObject> map, Team team, Random random){
        abilities = Lists.mutable.empty();
        actions = Lists.mutable.empty();
        stats = IntLists.mutable.of(0,0,0);
        this.map = map;
        this.team = team;
        this.random = random;
        cached = Maps.mutable.empty();
        team.addCharacter(this);
        this.sightRange = new Range(2);
        this.sliceRange = new Range(1, true);
        this.stepRange = new Range(1, true);
        this.delay = 100;
        this.actionQueue = actionQueue;
        this.eventManager = new EventManager();
        this.slice = new Slice(this);
        this.step = new Step(this);
        this.smile = new Smile(this);
        this.actions.add(slice);
        this.actions.add(step);
        this.actions.add(smile);
        this.silenceTurn = -1;
        this.poisonTurn = -1;
        this.frozenTurn = -1;
        this.invisibleTurn = -1;
        this.readonly = new ReadonlyCharacter(this);
        this.enemy = new EnemyCharacter(this);
    }

    public Random getRandom() {
        return random;
    }

    public ActionQueue getActionQueue() {
        return actionQueue;
    }

    public void start(){
        primary = Stat.values()[stats.indexOf(stats.max())];
        actionQueue.enqueue(this::action, 0);
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
            break;
            case AGI:
                delay -= DELAY_PER_AGI*amount;
            break;
            case INT:
                maxMana += MANA_PER_INT*amount;
                mana += MANA_PER_INT*amount;
            break;
        }
    }

    public boolean isStunned(){
        return stunTick > actionQueue.getTime();
    }

    public boolean isPoisoned(){
        return poisonTurn >= turn;
    }

    public int getPoisonAmount(){
        return isPoisoned()?poisonAmount:0;
    }

    public boolean isSilenced(){
        return silenceTurn >= turn;
    }

    public boolean isInvisible(){
        return invisibleTurn >= turn;
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
    }

    public void invisible(int duration){
        invisibleTurn = Math.max(actionQueue.getTime() + duration, invisibleTurn);
    }

    public void reveal(){
        invisibleTurn = 0;
    }

    public int action(){
        if (isDead()){
            return -1;
        }
        turn++;
        Player player = team.getPlayer();
        TurnStartEvent event = new TurnStartEvent();
        eventManager.addEvent(event, Events.TurnStart);
        if (event.isCanceled()){
            return delay;
        }
        if (isPoisoned()){
            damage(getPoisonAmount());
        }

        MutableSet<Action> actions = this.actions.select(Action::isAvailable).tap(Action::clearSelection).toSet();
        MutableMap<ReadonlyAction, Action> readonlyActions = actions.toMap(Action::readonly, i->i);
        player.setVisibleEnemies(visibleEnemies().toMap(BaseCharacter::getLocation, BaseCharacter::enemy));
        ReadonlyAction choice = player.choose(readonlyActions.keysView().toSet(), readonly);
        if (!readonlyActions.containsKey(choice)){
            throw new RuntimeException("Invalid action");
        }
        Action action = readonlyActions.get(choice);

        if (action.breaksInvisibility()){
            invisibleTurn = -1;
        }
        action.act();
        if (!action.basicAction()){
            lastAction = action;
        }
        return delay;
    }

    public void step(){
        if (isFrozen()){
            return;
        }
        step.perform();
    }

    public void step(Point2D location){
        if (isFrozen()){
            return;
        }
        StepEvent event = new StepEvent(this, location);
        eventManager.addEvent(event, Events.Step);
        if (event.isCanceled()){
            return;
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

    public void slice(){
        slice.perform();
    }

    public void slice(BaseCharacter character){
        SliceEvent event = new SliceEvent(this, character, stats.get(primary.ordinal()));
        eventManager.addEvent(event, Events.Slice);
        character.eventManager.addEvent(event, Events.Sliced);
        character.damage(this, event.getAmount());
    }

    public Stat primaryStat(){
        return primary;
    }

    public MutableSet<Point2D> rangeAround(Range range){
        return cached.getIfAbsentPut(range, Cache::new).get(() -> {
            MutableSet<Point2D> points = Sets.mutable.empty();
            points.add(currentLocation);
            GraphMap<Point2D, MapObject> map = getMap();
            if (range.isCardinal()) {
                for (int i = 1; i <= range.getRange(); i++) {
                    points.add(currentLocation.moveX(i));
                    points.add(currentLocation.moveY(i));
                    points.add(currentLocation.moveX(-i));
                    points.add(currentLocation.moveY(-i));
                }
            } else {
                points.addAll(map.getNeighbors(currentLocation, range.getRange()));
            }
            return points.select(map::inBounds);
        });
    }

    public MutableSet<BaseCharacter> teammates(Range range){
        return characters(range).intersect(teammates());
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
        return characters(range)
                .reject(team::contains)
                .reject(BaseCharacter::isInvisible)
                .select(p -> team.getCharacters().anySatisfy(c -> c.characters(c.getSightRange()).contains(p)));
    }

    public MutableSet<BaseCharacter> visibleEnemies(){
        return visibleEnemies(1000);
    }

    public MutableSet<BaseCharacter> characters(Range range){
        return rangeAround(range)
                .select(map::isFilled)
                .collect(map::get)
                .collectIf(i -> i instanceof BaseCharacter, i -> (BaseCharacter) i);
    }

    public void damage(BaseCharacter source, double amount){
        DamageEvent damageEvent = new DamageEvent(source, this, amount);
        eventManager.addEvent(damageEvent, Events.Damaged);
        damage(damageEvent.getAmount());
    }

    public void damage(double amount){
        this.health -= amount;
        if (this.health <= 0){
            die();
        }
    }

    public void die(){
        DeathEvent event = new DeathEvent();
        eventManager.addEvent(event, Events.Death);
        if (event.isCanceled()){
            return;
        }
        this.dead = true;
        this.team.removeCharacter(this);
        this.map.clear(currentLocation);
        currentLocation = null;
    }

    public void setLocation(Point2D location){
        if (map.inBounds(location) && getMap().isEmpty(location)) {
            cached.clear();
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
        this.mana = Math.max(this.mana + amount, maxMana);
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
