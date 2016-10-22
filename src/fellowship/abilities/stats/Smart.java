package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;

public class Smart implements CharacterAbility {

    @Override
    public void apply(BaseCharacter character) {
        character.setSmartness(character.smartness()+1);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
