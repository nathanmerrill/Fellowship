package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;

public class Weak implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.addAttributePoints(-15);
    }

    @Override
    public int getNumSlots() {
        return -1;
    }
}
