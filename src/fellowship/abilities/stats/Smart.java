package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;

public class Smart implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.setSmartness(character.smartness()+1);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
