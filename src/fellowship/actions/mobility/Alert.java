package fellowship.actions.mobility;

import fellowship.characters.BaseCharacter;
import fellowship.actions.CharacterAction;

public class Alert extends CharacterAction {

    public Alert(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.teamCharacters().forEach(BaseCharacter::step);
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public int getManaCost() {
        return 20;
    }
}
