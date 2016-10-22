package fellowship.actions.mobility;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;

import java.util.ArrayList;
import java.util.List;

public class Swap extends TargettedAction {

    public Swap(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        List<Character> characters = new ArrayList<>(character.enemyCharacters());
        characters.addAll(character.teamCharacters());
        return characters;
    }

    @Override
    public void perform(Character target) {
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
