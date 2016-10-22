package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.Range;

public class Ranged implements CharacterAbility{
    @Override
    public void apply(BaseCharacter character) {
        character.setStepRange(new Range(character.getSliceRange().getRange()+1, character.getSliceRange().isCardinal()));
    }
}
