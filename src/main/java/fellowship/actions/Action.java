package fellowship.actions;

import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.Range;
import fellowship.characters.BaseCharacter;
import fellowship.characters.ReadonlyCharacter;
import fellowship.events.Events;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Sets;

public abstract class Action {
    protected final BaseCharacter character;
    public int cooldown;
    private final ReadonlyAction readonly;
    private final Range defaultRange = new Range(0);

    public Action(BaseCharacter character) {
        this.character = character;
        cooldown = 0;
        this.readonly = new ReadonlyAction(this);
    }

    public final void act() {
        if (!isAvailable()) {
            throw new RuntimeException("Trying to execute an invalid action");
        }
        cooldown = getCooldown() * (5 - character.cleverness()) / 5;
        int manaCost = Math.max(0, getManaCost() - (2 * character.smartness()));
        this.character.removeMana(manaCost);
        perform();
        if (cooldown > 0) {
            character.on(Events.TurnStart, event -> (--cooldown) > 0);
        }
    }

    public Range getRange() {
        return defaultRange;
    }

    public abstract void perform();

    public abstract int getManaCost();

    public abstract int getCooldown();

    public final int getRemainingCooldown(){
        return cooldown;
    }

    public boolean isAvailable() {
        if (character.isStunned()){
            return false;
        }
        if (character.isSilenced() && !basicAction()){
            return false;
        }
        if (character.isFrozen() && movementAction()){
            return false;
        }
        return cooldown == 0
                && this.character.getMana() >= getManaCost();
    }

    public boolean basicAction() {
        return false;
    }

    public boolean breaksInvisibility() {
        return true;
    }

    public boolean movementAction() {
        return false;
    }

    public ReadonlyAction readonly() {
        return readonly;
    }

    public MutableSet<BaseCharacter> availableTargets() {
        return Sets.mutable.empty();
    }

    public MutableSet<Point2D> availableLocations() {
        return Sets.mutable.empty();
    }

    public void setTarget(ReadonlyCharacter target) {
        throw new RuntimeException("Action doesn't support setting a target");
    }

    public boolean needsTarget(){
        return false;
    }

    public void setLocation(Point2D location) {
        throw new RuntimeException("Action doesn't support setting a location");
    }

    public boolean needsLocation(){
        return false;
    }

    public void clearSelection() {

    }

    public String getName(){
        return getClass().getSimpleName();
    }

    public int getRemaining(){
        return 0;
    }
}
