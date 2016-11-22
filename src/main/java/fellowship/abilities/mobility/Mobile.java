package fellowship.abilities.mobility;

import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;

public class Mobile extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        character.setStepRange(character.getStepRange().notCardinal());
    }
}
