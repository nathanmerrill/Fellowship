package fellowship.abilities.attacking;

import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;

public class Ranged extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        character.setSliceRange(character.getSliceRange().longer(1));
    }
}
