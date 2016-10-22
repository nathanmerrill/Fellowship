package fellowship.abilities.attacking;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Range;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

import java.util.function.Consumer;

public class Flexible implements CharacterAbility{
    @Override
    public void apply(Character character) {
        character.setStepRange(new Range(character.getSliceRange().getRange()));
    }
}
