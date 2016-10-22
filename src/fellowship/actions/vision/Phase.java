package fellowship.actions.vision;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class Phase extends CharacterAction{

    public Phase(Character character){
        super(character);
    }

    @Override
    public void perform() {
        character.invisible(1);
    }

    @Override
    public int getCooldown() {
        return 3;
    }

    @Override
    public int getManaCost() {
        return 0;
    }
}
