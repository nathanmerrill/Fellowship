package fellowship.abilities.stats;

import fellowship.Character;
import fellowship.CharacterAbility;

public class Clever implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.setCleverness(character.cleverness()+1);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
