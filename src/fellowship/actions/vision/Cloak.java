package fellowship.actions.vision;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class Cloak extends CharacterAction{

    public Cloak(Character character){
        super(character);
    }

    @Override
    public void perform() {
        character.teamCharacters().forEach(teammate -> teammate.invisible(5));
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public int getManaCost() {
        return 20;
    }
}
