package fellowship.characters;

import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.Range;
import fellowship.Stat;
import fellowship.abilities.Ability;
import fellowship.abilities.ReadonlyAbility;
import fellowship.actions.Action;
import fellowship.actions.ReadonlyAction;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;

public final class EnemyCharacter implements CharacterInterface{
    private final BaseCharacter character;

    public EnemyCharacter(BaseCharacter character){
        this.character = character;
    }

    public int smartness(){
        return character.smartness();
    }

    public int cleverness(){
        return character.cleverness();
    }

    public int getStat(Stat stat){
        return character.getStat(stat);
    }

    public Range getSightRange(){
        return character.getSightRange();
    }

    public ReadonlyAction getLastAction(){
        Action lastAction = character.getLastAction();
        if (lastAction == null){
            return null;
        }
        return new ReadonlyAction(lastAction);
    }

    public boolean isFrozen(){
        return character.isFrozen();
    }

    public boolean isStunned(){
        return character.isStunned();
    }

    public boolean isPoisoned(){
        return character.isPoisoned();
    }

    public int getPoisonAmount(){
        return character.getPoisonAmount();
    }

    public boolean isSilenced(){
        return character.isSilenced();
    }

    public boolean isInvisible(){
        return character.isInvisible();
    }

    public boolean isDead(){
        return character.isDead();
    }

    public Stat primaryStat(){
        return character.primaryStat();
    }

    public Range getStepRange(){
        return character.getStepRange();
    }

    public Range getSliceRange() {
        return character.getSliceRange();
    }

    public MutableSet<Point2D> rangeAround(Range range, Point2D location){
        return BaseCharacter.rangeAround(range, location, character.getMap()).toSet();
    }

    public double getMana() {
        return character.getMana();
    }

    public int getMaxMana(){
        return character.getMaxMana();
    }

    public double getManaRegen() {
        return character.getManaRegen();
    }

    public double getHealth(){
        return character.getHealth();
    }

    public int getMaxHealth(){
        return character.getMaxHealth();
    }

    public double getHealthRegen() {
        return character.getHealthRegen();
    }

    public MutableList<ReadonlyAbility> getAbilities(){
        return character.getAbilities().collect(Ability::readonly);
    }

    public Class<? extends BaseCharacter> characterClass(){
        return character.getClass();
    }

    @Override
    public MutableList<ReadonlyAction> getActions() {
        return character.getActions().collect(Action::readonly);
    }

    @Override
    public int getNextTick() {
        return character.getNextTick();
    }

    @Override
    public int getStunTick() {
        return character.getStunTick();
    }
}
