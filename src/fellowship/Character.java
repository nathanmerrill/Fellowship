package fellowship;

import com.ppcg.kothcomm.game.maps.gridmaps.GridMap;
import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.actions.CharacterAction;
import fellowship.actions.attacking.Slice;
import fellowship.actions.mobility.Step;
import fellowship.actions.other.Smile;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;
import fellowship.events.TurnStartEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Character extends MapObject implements ReadonlyCharacter{
    public final static int STARTING_ATTRIBUTE_POINTS = 20;
    private Team team;
    private final List<CharacterAbility> abilities;
    private final List<CharacterAction> actions;
    private final CharacterAction slice, step, smile;
    private int attributePoints;
    private int[] stats = new int[Stat.values().length];
    private int smartness, cleverness;
    private double health, mana;
    private int maxHealth, maxMana;
    private int delay;
    private int turnNumber;
    private int stunTick;
    private int poisonTurn, poisonAmount;
    private int frozenTurn;
    private int silenceTurn;
    private int invisibleTurn;
    private int turn;
    private boolean dead;
    private Range sightRange, sliceRange, stepRange;
    private ActionQueue actionQueue;
    private CharacterAction lastAction;
    private EventManager eventManager;
    private Point2D currentLocation;


    public Character(ActionQueue actionQueue){
        abilities = new ArrayList<>();
        actions = new ArrayList<>();
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
        return stats[stat.ordinal()];
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
        stats[stat.ordinal()] += amount;
        switch (stat){
            case STR:
                maxHealth += 10*amount;
                heal(10*amount);
            break;
            case AGI:
                delay -= amount;
            break;
            case INT:
                maxMana += 7*amount;
                mana += 7*amount;
            break;
        }
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
        turn++;
        TurnStartEvent event = new TurnStartEvent();
        eventManager.addEvent(event);
        if (event.isCanceled()){
            return delay;
        }
        if (poisonTurn >= turn){
            damage(poisonAmount);
        }
        if (dead){
            return -1;
        }
        Stream<CharacterAction> actionStream = actions.stream()
                    .filter(CharacterAction::isAvailable);
        int currentTick = actionQueue.getTime();
        if (stunTick > currentTick){
            actionStream = actionStream.filter(i -> i instanceof Smile);
        }
        if (silenceTurn >= turn){
            actionStream = actionStream.filter(CharacterAction::basicAction);
        }
        if (frozenTurn >= turn){
            actionStream = actionStream.filter(i -> !i.movementAction());
        }
        Set<CharacterAction> availableActions = actionStream.collect(Collectors.toSet());
        CharacterAction choice = team.getPlayer().choose(availableActions, this);
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
        if (frozenTurn >= turn){
            return;
        }
        Set<Point2D> options = rangeAround(stepRange);
        Point2D location = team.getPlayer().step(options, this);
        if (options.contains(location)){
            getMap().move(currentLocation, location);
            currentLocation = location;
        }
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
        if (poisonTurn >= turn){
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

    public void slice(Character character){
        SliceEvent event = new SliceEvent();

    }

    public Stat primaryStat(){

    }

    public Set<Point2D> rangeAround(Range range){

    }

    public Character getTargetFor(CharacterAction action, List<Character> options){

    }

    public Point2D getLocationFor(CharacterAction action, int range){

    }

    public List<Character> teamCharacters(int range){
        //Exclude self
    }

    public List<Character> teamCharacters(){

    }

    public List<Character> enemyCharacters(int range){

    }

    public List<Character> enemyCharacters(Range range){

    }

    public List<Character> enemyCharacters(){

    }

    public void damage(Character source, double amount){

    }

    public void damage(int amount){

    }

    public void setLocation(Point2D location){
        //Freeze check
    }

    public Point2D getLocation(){

    }

    public Range getStepRange(){

    }

    public void setStepRange(Range range){

    }

    public Range getSliceRange(){

    }

    public void setSliceRange(Range range){

    }

    public int getMana(){

    }

    public void removeMana(double amount){

    }

    public int getMaxMana(){

    }

    public int getHealth(){

    }

    public int getMaxHealth(){

    }

    public void setMaxHealth(int max){

    }

    public List<CharacterAbility> getAbilities(){
        return new ArrayList<>(abilities);
    }

    public void setTeam(Team team) {
        if (this.team == null) {
            this.team = team;
            team.addCharacter(this);
        }
    }

    public Team getTeam() {
        return team;
    }

    public GridMap<Point2D, MapObject> getMap(){

    }


}
