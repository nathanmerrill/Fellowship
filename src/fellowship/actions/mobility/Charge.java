package fellowship.actions.mobility;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class Charge extends CharacterAction {

    public Charge(Character character){
        super(character);
    }

    @Override
    public void perform() {
        character.step();
        character.slice();
    }

    @Override
    public int getManaCost() {
        return 2;
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public boolean movementAction() {
        return true;
    }
}
