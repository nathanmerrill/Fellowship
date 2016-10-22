package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class Cleave implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Slice, Event.forever(i -> {
            SliceEvent event = (SliceEvent)i;
            BaseCharacter target = event.getSliced();
            double damage = event.getAmount()/2;
            target.teamCharacters(1).forEach(c -> c.damage(character, damage));
        }));
    }
}
