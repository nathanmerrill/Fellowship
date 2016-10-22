package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;

public class Strong implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.addAttributePoints(10);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
