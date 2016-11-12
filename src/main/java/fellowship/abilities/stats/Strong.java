package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;

public class Strong extends Ability {

    @Override
    public void apply(BaseCharacter character) {
    }

    @Override
    public int attributePoints() {
        return 10;
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
