package fellowship.actions.mobility;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class Alert extends CharacterAction {

    public Alert(Character character){
        super(character);
    }

    @Override
    public void perform() {
        character.teamCharacters().forEach(Character::step);
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
