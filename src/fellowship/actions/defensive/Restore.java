package fellowship.actions.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;

public class Restore extends Action {

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
