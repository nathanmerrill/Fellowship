package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;

public class Weak extends Ability {

    @Override
    public void apply(BaseCharacter character) {

    }

    @Override
    public int attributePoints() {
        return -15;
    }

    @Override
    public int getNumSlots() {
        return -1;
    }
}
