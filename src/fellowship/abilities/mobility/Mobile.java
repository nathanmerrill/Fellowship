package fellowship.abilities.mobility;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.Range;

public class Mobile implements CharacterAbility{
    @Override
    public void apply(BaseCharacter character) {
        character.setStepRange(new Range(character.getStepRange().getRange()+1, character.getStepRange().isCardinal()));
    }
}
