package fellowship.actions.other;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.WallObject;
import fellowship.actions.LocationAction;

public class Wall extends LocationAction {

    public Wall(Character character){
        super(character, 6);
    }

    @Override
    public void perform(Point2D location) {
        character.getMap().put(location, new WallObject());
    }

    @Override
    public int getCooldown() {
        return 2;
    }

    @Override
    public int getManaCost() {
        return 10;
    }
}
