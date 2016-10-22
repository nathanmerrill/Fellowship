package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.Range;

public class Flexible implements Ability {
    @Override
    public void apply(BaseCharacter character) {
        character.setStepRange(new Range(character.getSliceRange().getRange()));
    }
}
