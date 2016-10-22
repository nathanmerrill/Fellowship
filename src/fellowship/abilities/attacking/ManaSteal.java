package fellowship.abilities.attacking;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Stat;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class ManaSteal implements CharacterAbility{

    @Override
    public void apply(Character character) {
        character.on(Events.Slice, Event.forever(i -> {
            SliceEvent event = (SliceEvent)i;
            Character target = event.getSliced();
            character.removeMana(-2);
            target.removeMana(2);
        }));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
