package fellowship.actions.statuses;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;

import java.util.List;

public class Knockout extends TargettedAction {

    public Knockout(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public void perform(Character target) {
        target.stun(1000);
        character.stun(1000);
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public int getManaCost() {
        return 10;
    }
}
