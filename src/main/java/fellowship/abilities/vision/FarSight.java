package fellowship.abilities.vision;

import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;

public class FarSight extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.setSightRange(character.getSightRange().longer(2));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
