package fellowship.actions.mobility;

import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.Range;
import fellowship.actions.LocationAction;

public class Step extends LocationAction {

    public Step(BaseCharacter character){
        super(character);
    }

    @Override
    public Range getRange() {
        return character.getStepRange();
    }

    @Override
    public void perform(Point2D point) {
        character.step(getLocation());
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public boolean basicAction() {
        return true;
    }

    @Override
    public boolean movementAction() {
        return true;
    }

    @Override
    public boolean breaksInvisibility() {
        return false;
    }
}
