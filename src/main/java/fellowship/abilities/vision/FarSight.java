package fellowship.abilities.vision;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.Range;

public class FarSight extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.setSightRange(new Range(character.getSightRange().getRange()+2));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
