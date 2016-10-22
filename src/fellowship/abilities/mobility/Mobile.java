package fellowship.abilities.mobility;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Range;

public class Mobile implements CharacterAbility{
    @Override
    public void apply(Character character) {
        character.setStepRange(new Range(character.getStepRange().getRange()+1, character.getStepRange().isCardinal()));
    }
}
