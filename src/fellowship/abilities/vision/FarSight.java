package fellowship.abilities.vision;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Range;
import fellowship.events.Event;
import fellowship.events.Events;

public class FarSight implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.setSightRange(new Range(character.getSightRange().getRange()));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
