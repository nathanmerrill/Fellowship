package fellowship.actions.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.actions.CharacterAction;

public class Restore extends CharacterAction {

    public Restore(BaseCharacter character){
        super(character);
    }
    @Override
    public void perform() {
        character.teamCharacters().forEach(teammate -> teammate.heal(teammate.getMaxHealth()));
    }

    @Override
    public int getCooldown() {
        return 40;
    }

    @Override
    public int getManaCost() {
        return 20;
    }
}
