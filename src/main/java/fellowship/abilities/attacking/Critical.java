package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

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
