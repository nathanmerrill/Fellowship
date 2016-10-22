package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class ManaSteal implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Slice, Event.forever(i -> {
            SliceEvent event = (SliceEvent)i;
            BaseCharacter target = event.getSliced();
            character.addMana(2);
            target.removeMana(2);
        }));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
