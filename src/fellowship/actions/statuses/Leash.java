package fellowship.actions.statuses;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;

import java.util.List;

public class Leash extends TargettedAction {

    public Leash(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public void perform(Character target) {
        target.freeze(2);
    }

    @Override
    public int getCooldown() {
        return 6;
    }

    @Override
    public int getManaCost() {
        return 4;
    }
}
