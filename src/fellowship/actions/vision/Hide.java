package fellowship.actions.vision;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class Hide extends CharacterAction{

    public Hide(Character character){
        super(character);
    }

    @Override
    public void perform() {
        character.invisible(5);
    }

    @Override
    public int getCooldown() {
        return 7;
    }

    @Override
    public int getManaCost() {
        return 4;
    }
}
