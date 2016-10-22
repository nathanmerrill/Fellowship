package fellowship.actions.vision;

import fellowship.characters.BaseCharacter;
import fellowship.actions.CharacterAction;

public class Cloak extends CharacterAction{

    public Cloak(BaseCharacter character){
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
