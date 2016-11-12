package fellowship.abilities.mobility;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.Range;

public class Mobile extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        character.setStepRange(new Range(character.getStepRange().getRange()+1, character.getStepRange().isCardinal()));
    }
}
