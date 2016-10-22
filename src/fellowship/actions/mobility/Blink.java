package fellowship.actions.mobility;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.actions.LocationAction;

public class Blink extends LocationAction {

    public Blink(BaseCharacter character){
        super(character, 4);
    }
    @Override
    public void perform(Point2D location) {
        character.setLocation(location);
    }

    @Override
    public int getCooldown() {
        return 2;
    }

    @Override
    public int getManaCost() {
        return 2;
    }

    @Override
    public boolean movementAction() {
        return true;
    }
}
