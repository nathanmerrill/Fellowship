package fellowship.actions.mobility;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class Run extends CharacterAction {

    public Run(Character character){
        super(character);
    }

    @Override
    public void perform() {
        character.step();
        character.step();
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 1;
    }

    @Override
    public boolean movementAction() {
        return true;
    }

    @Override
    public boolean basicAction() {
        return true;
    }
}
