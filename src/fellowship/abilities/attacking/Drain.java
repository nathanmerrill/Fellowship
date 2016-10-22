package fellowship.abilities.attacking;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class Drain implements CharacterAbility{

    @Override
    public void apply(Character character) {
        character.on(Events.Slice, Event.forever(i -> character.heal(3)));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
