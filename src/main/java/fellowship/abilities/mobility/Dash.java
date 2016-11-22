package fellowship.abilities.mobility;

import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;

public class Dash extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        character.setStepRange(character.getStepRange().longer(1));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
