package fellowship.actions.mobility;

import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;

public class Swap extends TargettedAction {

    public Swap(BaseCharacter character){
        super(character);
    }

    @Override
    public boolean canTargetTeam() {
        return true;
    }

    @Override
    public void perform(BaseCharacter target) {
        Point2D targetLocation = target.getLocation();
        Point2D currentLocation = character.getLocation();
        target.setLocation(currentLocation);
        character.setLocation(targetLocation);
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public int getManaCost() {
        return 5;
    }

    @Override
    public boolean movementAction() {
        return true;
    }
}
