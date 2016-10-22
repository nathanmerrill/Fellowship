package fellowship.abilities.stats;

import fellowship.Character;
import fellowship.CharacterAbility;

public class Strong implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.addAttributePoints(10);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
