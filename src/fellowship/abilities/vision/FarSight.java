package fellowship.abilities.vision;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.Range;

public class FarSight implements CharacterAbility {

    @Override
    public void apply(BaseCharacter character) {
        character.setSightRange(new Range(character.getSightRange().getRange()));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
