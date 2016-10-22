package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;

public class Clever implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.setCleverness(character.cleverness()+1);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
