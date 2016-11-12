package fellowship.actions.other;

import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.WallObject;
import fellowship.actions.LocationAction;

public class Wall extends LocationAction {

    public Wall(BaseCharacter character){
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
