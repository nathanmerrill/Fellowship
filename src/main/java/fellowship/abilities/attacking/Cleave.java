package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class Cleave extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Slice, Event.forever(i -> {
            SliceEvent event = (SliceEvent)i;
            BaseCharacter target = event.getSliced();
            double damage = event.getAmount()/2;
            target.teammates(1).forEach(c -> c.damage(character, damage));
        }));
    }
}
