package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.Range;

public class Flexible extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        character.setStepRange(new Range(character.getStepRange().getRange()));
    }
}
