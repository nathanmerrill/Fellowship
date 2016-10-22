package fellowship.abilities.attacking;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Range;

public class Ranged implements CharacterAbility{
    @Override
    public void apply(Character character) {
        character.setStepRange(new Range(character.getSliceRange().getRange()+1, character.getSliceRange().isCardinal()));
    }
}
