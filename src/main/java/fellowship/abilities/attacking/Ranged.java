package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.Range;

public class Ranged extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        character.setSliceRange(new Range(character.getSliceRange().getRange()+1, character.getSliceRange().isCardinal()));
    }
}
