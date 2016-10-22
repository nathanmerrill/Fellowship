package fellowship.actions.mobility;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import org.eclipse.collections.api.set.MutableSet;

public class Swap extends TargettedAction {

    public Swap(BaseCharacter character){
        super(character);
    }

    @Override
    protected MutableSet<BaseCharacter> getAvailableTargets() {
        return character.enemyCharacters().withAll(character.teamCharacters());
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
