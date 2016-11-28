package fellowship.abilities.attacking;

import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;

public class Flexible extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        character.setSliceRange(character.getSliceRange().notCardinal());
    }
}