package fellowship.characters;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;

import fellowship.Range;
import fellowship.Stat;
import fellowship.abilities.AbilityInterface;
import fellowship.actions.ActionInterface;
import fellowship.teams.TeamInterface;
import org.eclipse.collections.api.list.MutableList;

public interface CharacterInterface {

    int smartness();

    int cleverness();

    int getStat(Stat stat);

    Range getSightRange();

    ActionInterface getLastAction();

    boolean isFrozen();

    boolean isStunned();

    boolean isPoisoned();

    int getPoisonAmount();

    boolean isSilenced();

    boolean isInvisible();

    boolean isDead();

    Stat primaryStat();

    Point2D getLocation();

    Range getStepRange();

    Range getSliceRange();

    double getMana();

    int getMaxMana();

    double getHealth();

    int getMaxHealth();

    MutableList<? extends AbilityInterface> getAbilities();

    TeamInterface getTeam();

    Class<? extends CharacterInterface> characterClass();

}
