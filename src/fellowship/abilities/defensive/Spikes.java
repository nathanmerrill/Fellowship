package fellowship.abilities.defensive;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.events.DamageEvent;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

import java.util.Random;

public class Spikes implements CharacterAbility{

    @Override
    public void apply(Character character) {
        character.on(Events.Damaged, Event.forever(i -> {
            DamageEvent event = (DamageEvent)i;
            event.getDamager().damage(character, event.getAmount()/2);
        }));
    }

}
