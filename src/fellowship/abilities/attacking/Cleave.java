package fellowship.abilities.attacking;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.actions.attacking.Slice;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

import java.util.function.Consumer;

public class Cleave implements CharacterAbility{

    @Override
    public void apply(Character character) {
        character.on(Events.Slice, Event.forever(i -> {
            SliceEvent event = (SliceEvent)i;
            Character target = event.getSliced();
            double damage = event.getAmount()/2;
            target.teamCharacters(1).forEach(c -> c.damage(character, damage));
        }));
    }
}
