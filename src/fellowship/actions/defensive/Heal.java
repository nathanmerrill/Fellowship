package fellowship.actions.defensive;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;

import java.util.List;

public class Heal extends TargettedAction {

    public Heal(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.teamCharacters();
    }

    @Override
    public void perform(Character target) {
        target.heal(20);
    }

    @Override
    public int getCooldown() {
        return 3;
    }

    @Override
    public int getManaCost() {
        return 10;
    }
}
