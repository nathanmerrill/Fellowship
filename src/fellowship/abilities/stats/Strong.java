package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;

public class Strong implements CharacterAbility {

    @Override
    public void apply(BaseCharacter character) {
        character.addAttributePoints(10);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
