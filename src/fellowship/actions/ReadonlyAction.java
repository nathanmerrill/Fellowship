package fellowship.actions;


import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.characters.ReadonlyCharacter;
import org.eclipse.collections.api.set.MutableSet;

@SuppressWarnings("unused")
public final class ReadonlyAction {
    private final Action action;

    public ReadonlyAction(Action action) {
        this.action = action;
    }

    public int getManaCost() {
        return action.getManaCost();
    }

    public int getCooldown() {
        return action.getCooldown();
    }

    public boolean isAvailable() {
        return action.isAvailable();
    }

    public boolean basicAction() {
        return action.basicAction();
    }

    public boolean breaksInvisibility() {
        return action.breaksInvisibility();
    }

    public boolean movementAction() {
        return action.movementAction();
    }

    public MutableSet<ReadonlyCharacter> availableTargets() {
        return action.availableTargets().collect(BaseCharacter::readonly);
    }

    public MutableSet<Point2D> availableLocations() {
        return action.availableLocations();
    }


    public boolean needsTarget(){
        return action.needsTarget();
    }

    public boolean needsLocation(){
        return action.needsLocation();
    }

    public void setTarget(ReadonlyCharacter target) {
        action.setTarget(target);
    }

    public void setLocation(Point2D location) {
        action.setLocation(location);
    }

    public String getName(){
        return action.getName();
    }
}
