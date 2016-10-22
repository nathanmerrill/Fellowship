package fellowship.characters;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.Range;
import fellowship.Stat;
import fellowship.abilities.ReadonlyCharacterAbility;
import fellowship.actions.ReadonlyCharacterAction;
import fellowship.teams.ReadonlyTeam;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;

public class ReadonlyCharacter implements CharacterInterface {
    private final BaseCharacter character;
    public ReadonlyCharacter(BaseCharacter character){
        this.character = character;
    }

    BaseCharacter getCharacter() {
        return character;
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

    public ReadonlyCharacterAction getLastAction(){
        return new ReadonlyCharacterAction(character.getLastAction());
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

    public MutableSet<ReadonlyCharacter> teamCharacters(Range range){
        return character.teamCharacters(range).collect(ReadonlyCharacter::new);
    }

    public MutableSet<ReadonlyCharacter> teamCharacters(int range){
        return character.teamCharacters(range).collect(ReadonlyCharacter::new);
    }

    public MutableSet<ReadonlyCharacter> teamCharacters(){
        return character.teamCharacters().collect(ReadonlyCharacter::new);
    }

    public MutableSet<EnemyCharacter> enemyCharacters(Range range){
        return character.enemyCharacters(range).collect(EnemyCharacter::new);
    }

    public MutableSet<EnemyCharacter> enemyCharacters(int range){
        return character.enemyCharacters(range).collect(EnemyCharacter::new);
    }

    public MutableSet<EnemyCharacter> enemyCharacters(){
        return character.enemyCharacters().collect(EnemyCharacter::new);
    }

    public Stat primaryStat(){
        return character.primaryStat();
    }

    public Point2D getLocation(){
        return character.getLocation();
    }

    public Range getStepRange(){
        return character.getStepRange();
    }

    public Range getSliceRange() {
        return character.getSliceRange();
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

    public MutableList<ReadonlyCharacterAbility> getAbilities(){
        return character.getAbilities().collect(ReadonlyCharacterAbility::new);
    }

    public ReadonlyTeam getTeam() {
        return new ReadonlyTeam(character.getTeam());
    }


    public Class<? extends BaseCharacter> characterClass(){
        return character.getClass();
    }

}
