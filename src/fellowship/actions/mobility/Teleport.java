package fellowship.actions.mobility;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.actions.LocationAction;

public class Teleport extends LocationAction {
    public Teleport(BaseCharacter character){
        super(character);
    }
    @Override
    public void perform(Point2D location) {
        character.setLocation(location);
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public int getManaCost() {
        return 20;
    }

    @Override
    public boolean movementAction() {
        return true;
    }
}
