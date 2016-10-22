package fellowship.characters;

import com.ppcg.kothcomm.game.maps.gridmaps.GridMap;
import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.*;
import fellowship.abilities.CharacterAbility;
import fellowship.actions.CharacterAction;
import fellowship.actions.ReadonlyCharacterAction;
import fellowship.actions.attacking.Slice;
import fellowship.actions.mobility.Step;
import fellowship.actions.other.Smile;
import fellowship.teams.Team;
import fellowship.events.*;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.util.function.Function;

public class BaseCharacter implements MapObject, CharacterInterface {
    public final static int STARTING_ATTRIBUTE_POINTS = 20;
    public final static double MANA_REGEN_PER_INT = .1;
    public final static double HEALTH_REGEN_PER_STR = .5;
    public final static int MANA_PER_INT = 7;
    public final static int HEALTH_PER_STR = 10;
    public final static int DELAY_PER_AGI = 1;

    private final Team team;
    private final GridMap<Point2D, MapObject> map;
    private final ActionQueue actionQueue;
    private final MutableList<CharacterAbility> abilities;
    private final MutableList<CharacterAction> actions;
    private final CharacterAction slice, step, smile;
    private final MutableIntList stats;
    private Stat primary;
    private int attributePoints;
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
    private CharacterAction lastAction;
    private EventManager eventManager;
    private Point2D currentLocation;


    public BaseCharacter(ActionQueue actionQueue, GridMap<Point2D, MapObject> map, Team team){
        abilities = Lists.mutable.empty();
        actions = Lists.mutable.empty();
        stats = IntLists.mutable.of(0,0,0);
        this.map = map;
        this.team = team;
        team.addCharacter(this);
        this.attributePoints = STARTING_ATTRIBUTE_POINTS;
        this.sightRange = new Range(2);
        this.sliceRange = new Range(1, true);
        this.stepRange = new Range(1, true);
        this.delay = 100;
        this.actionQueue = actionQueue;
        this.eventManager = new EventManager();
        this.turn = -1;
        this.slice = new Slice(this);
        this.step = new Step(this);
        this.smile = new Smile(this);
        this.actions.add(slice);
        this.actions.add(step);
        this.actions.add(smile);
    }

    public ActionQueue getActionQueue() {
        return actionQueue;
    }

    public void start(){
        primary = Stat.values()[stats.indexOf(stats.max())];
        actionQueue.enqueue(this::action, 0);
    }

    public void addAttributePoints(int points){
        attributePoints += points;
    }

    public int getAttributePoints() {
        return attributePoints;
    }

    public void removeAttributePoints(int points){
        attributePoints -= points;
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

    public void addAction(CharacterAction action){
        actions.add(action);
    }

    public void removeAction(CharacterAction action){
        actions.remove(action);
    }

    public Range getSightRange(){
        return sightRange;
    }

    public void setSightRange(Range range){
        this.sightRange = range;
    }

    public void addStat(Stat stat, int amount){
        stats.addAtIndex(stat.ordinal(), amount);
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

    public void addAbility(CharacterAbility ability){
        abilities.add(ability);
    }

    public CharacterAction getLastAction(){
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
        player.setCurrentCharacter(new ReadonlyCharacter(this));
        TurnStartEvent event = new TurnStartEvent();
        eventManager.addEvent(event, Events.TurnStart);
        if (event.isCanceled()){
            return delay;
        }
        if (isPoisoned()){
            damage(getPoisonAmount());
        }
        MutableList<CharacterAction> actions = this.actions.select(CharacterAction::isAvailable);

        if (isStunned()){
            actions = actions.selectWith(Object::equals, smile);
        }
        if (isSilenced()){
            actions = actions.select(CharacterAction::basicAction);
        }
        if (isFrozen()){
            actions = actions.reject(CharacterAction::movementAction);
        }
        MutableSet<ReadonlyCharacterAction> availableActions = actions.collect(ReadonlyCharacterAction::new).toSet();

        CharacterAction choice = player.choose(availableActions);
        if (availableActions.contains(choice)){
            if (choice.breaksInvisibility()){
                invisibleTurn = -1;
            }
            choice.perform();
            if (!choice.basicAction()){
                lastAction = choice;
            }
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
        MutableSet<Point2D> points = Sets.mutable.empty();
        points.add(currentLocation);
        GridMap<Point2D, MapObject> map = getMap();
        if (range.isCardinal()){
            for (int i = 1; i <= range.getRange(); i++){
                points.add(currentLocation.moveX(i));
                points.add(currentLocation.moveY(i));
                points.add(currentLocation.moveX(-i));
                points.add(currentLocation.moveY(-i));
            }
            points.removeIf(map::contains);
        } else {
            points.addAll(map.getNeighbors(currentLocation, range.getRange()));
        }
        return points;
    }

    public BaseCharacter getTargetFor(CharacterAction action, MutableSet<BaseCharacter> options){
        Player player = team.getPlayer();
        player.setCurrentAction(new ReadonlyCharacterAction(action));
        player.setCurrentCharacter(new ReadonlyCharacter(this));
        ReadonlyCharacter readonly = team.getPlayer().target(options.collect(ReadonlyCharacter::new));
        if (readonly == null){
            return null;
        }
        BaseCharacter character = readonly.getCharacter();
        if (options.contains(character)){
            return character;
        }
        return null;
    }

    public Point2D getLocationFor(CharacterAction action, Range range){
        Player player = team.getPlayer();
        player.setCurrentAction(new ReadonlyCharacterAction(action));
        player.setCurrentCharacter(new ReadonlyCharacter(this));
        return player.locate(rangeAround(range).reject(this.getMap()::isFilled));
    }

    public MutableSet<BaseCharacter> teamCharacters(Range range){
        return characters(range).intersect(teamCharacters());
    }

    public MutableSet<BaseCharacter> teamCharacters(int range){
        return teamCharacters(new Range(range));
    }

    public MutableSet<BaseCharacter> teamCharacters(){
        return team.getCharacters().toSet().without(this);
    }

    public MutableSet<BaseCharacter> enemyCharacters(int range){
        return enemyCharacters(new Range(range));
    }

    public MutableSet<BaseCharacter> enemyCharacters(Range range){
        return characters(range).reject(team::contains).reject(BaseCharacter::isInvisible);
    }

    public MutableSet<BaseCharacter> enemyCharacters(){
        return enemyCharacters(1000);
    }

    public MutableSet<BaseCharacter> characters(Range range){
        return rangeAround(range)
                .select(getMap()::isFilled)
                .collect(getMap()::get)
                .collectIf(i -> i instanceof BaseCharacter, i -> (BaseCharacter) i);
    }

    public void damage(BaseCharacter source, double amount){
        DamageEvent damageEvent = new DamageEvent(source, this, amount);
        eventManager.addEvent(damageEvent, Events.Damaged);
        damage(damageEvent.getAmount());
    }

    public void damage(double amount){
        this.health -= amount;

    }

    public void die(){
        this.dead = true;
        this.team.removeCharacter(this);
        this.getMap().clear(currentLocation);
        currentLocation = null;
    }

    public void setLocation(Point2D location){
        if (getMap().contains(location) && getMap().isEmpty(location)) {
            getMap().move(currentLocation, location);
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

    public MutableList<CharacterAbility> getAbilities(){
        return abilities.clone();
    }

    @Override
    public Team getTeam() {
        return team;
    }

    public GridMap<Point2D, MapObject> getMap(){
        return map;
    }

    @Override
    public Class<? extends BaseCharacter> characterClass() {
        return getClass();
    }
}
