package fellowship.abilities.stats;

import fellowship.Character;
import fellowship.CharacterAbility;

public class Smart implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.setSmartness(character.smartness()+1);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
