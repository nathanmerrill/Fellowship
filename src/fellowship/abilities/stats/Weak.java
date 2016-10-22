package fellowship.abilities.stats;

import fellowship.Character;
import fellowship.CharacterAbility;

public class Weak implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.addAttributePoints(-15);
    }

    @Override
    public int getNumSlots() {
        return -1;
    }
}
