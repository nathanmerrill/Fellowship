package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;

public class Drain implements CharacterAbility{

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Slice, Event.forever(i -> character.heal(3)));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
