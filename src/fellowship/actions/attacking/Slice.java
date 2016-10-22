package fellowship.actions.attacking;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;

import java.util.List;

public class Slice extends TargettedAction {

    public Slice(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters(character.getSliceRange());
    }

    @Override
    public void perform(Character target) {
        character.slice(target);
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
}
