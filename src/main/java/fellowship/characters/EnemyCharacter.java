package fellowship.characters;

import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.*;
import fellowship.abilities.Ability;
import fellowship.abilities.ReadonlyAbility;
import fellowship.actions.ReadonlyAction;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;

public final class EnemyCharacter {
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
        return new ReadonlyAction(character.getLastAction());
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

    public MutableMap<Point2D, EnemyCharacter> visibleEnemies(Range range){
        return character.visibleEnemies(range).toMap(BaseCharacter::getLocation, BaseCharacter::enemy);
    }

    public MutableMap<Point2D, EnemyCharacter> visibleEnemies(int range){
        return character.visibleEnemies(range).toMap(BaseCharacter::getLocation, BaseCharacter::enemy);
    }

    public MutableMap<Point2D, EnemyCharacter> visibleEnemies(){
        return character.visibleEnemies().toMap(BaseCharacter::getLocation, BaseCharacter::enemy);
    }

    public double getMana() {
        return character.getMana();
    }

    public int getMaxMana(){
        return character.getMaxMana();
    }

    public double getHealth(){
        return character.getHealth();
    }

    public int getMaxHealth(){
        return character.getMaxHealth();
    }

    public MutableList<ReadonlyAbility> getAbilities(){
        return character.getAbilities().collect(Ability::readonly);
    }

    public Class<? extends BaseCharacter> characterClass(){
        return character.getClass();
    }

}
