package fellowship.actions.mobility;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class Step extends CharacterAction {

    public Step(Character character){
        super(character);
    }

    @Override
    public void perform() {
        character.step();
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

    @Override
    public boolean movementAction() {
        return true;
    }
}
