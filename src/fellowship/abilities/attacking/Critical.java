package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

import java.util.Random;

public class Critical extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Slice, Event.forever(i -> {
            SliceEvent event = (SliceEvent)i;
            if (character.getRandom().nextDouble() < .3){
                event.setAmount(event.getAmount()*2);
            }
        }));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
