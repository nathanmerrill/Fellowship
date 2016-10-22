package fellowship.abilities.mobility;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Range;

public class Dash implements CharacterAbility{
    @Override
    public void apply(Character character) {
        character.setStepRange(new Range(character.getStepRange().getRange()));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
