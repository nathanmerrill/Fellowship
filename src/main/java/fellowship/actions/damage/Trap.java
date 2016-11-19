package fellowship.actions.damage;

import com.nmerrill.kothcomm.game.maps.Point2D;
import com.nmerrill.kothcomm.game.maps.graphmaps.GraphMap;
import fellowship.MapObject;
import fellowship.TrapStack;
import fellowship.actions.LocationAction;
import fellowship.characters.BaseCharacter;

public class Trap extends LocationAction {

    public Trap(BaseCharacter character){
        super(character, 4);
    }

    @Override
    public void perform(Point2D location) {
        TrapStack stack;
        GraphMap<Point2D, MapObject> map = character.getMap();
        if (map.isFilled(location)){
            stack = (TrapStack) map.get(location);
        } else {
            stack = new TrapStack();
            map.put(location, stack);
        }
        stack.addDamage(character.getTeam().getEnemyTeam());
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
