package fellowship.characters;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.*;
import fellowship.abilities.ReadonlyAbility;
import fellowship.actions.ReadonlyAction;
import fellowship.teams.EnemyTeam;
import org.eclipse.collections.api.list.MutableList;

public class EnemyCharacter implements CharacterInterface {
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

    public MutableList<ReadonlyAbility> getAbilities(){
        return character.getAbilities().collect(ReadonlyAbility::new);
    }

    public EnemyTeam getTeam() {
        return new EnemyTeam(character.getTeam());
    }


    public Class<? extends BaseCharacter> characterClass(){
        return character.getClass();
    }

}
