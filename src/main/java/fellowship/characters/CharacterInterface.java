package fellowship.characters;

import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.Range;
import fellowship.Stat;
import fellowship.abilities.ReadonlyAbility;
import fellowship.actions.ReadonlyAction;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;

public interface CharacterInterface {
    int smartness();

    int cleverness();

    int getStat(Stat stat);

    Range getSightRange();

    ReadonlyAction getLastAction();

    boolean isFrozen();

    boolean isStunned();

    boolean isPoisoned();

    int getPoisonAmount();

    boolean isSilenced();

    boolean isInvisible();

    boolean isDead();

    Stat primaryStat();

    Range getStepRange();

    Range getSliceRange();

    MutableSet<Point2D> rangeAround(Range range, Point2D location);

    double getMana();

    int getMaxMana();

    double getManaRegen();

    double getHealth();

    int getMaxHealth();

    double getHealthRegen();

    MutableList<ReadonlyAbility> getAbilities();

    MutableList<ReadonlyAction> getActions();

    Class<? extends BaseCharacter> characterClass();

    int getStunTick();

    int getNextTick();

}
