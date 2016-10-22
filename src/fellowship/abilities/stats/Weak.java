package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;

public class Weak implements CharacterAbility {

    @Override
    public void apply(BaseCharacter character) {
        character.addAttributePoints(-15);
    }

    @Override
    public int getNumSlots() {
        return -1;
    }
}
