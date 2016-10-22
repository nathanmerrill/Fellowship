package fellowship.abilities.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.events.DamageEvent;
import fellowship.events.Event;
import fellowship.events.Events;

public class Spikes implements CharacterAbility{

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Damaged, Event.forever(i -> {
            DamageEvent event = (DamageEvent)i;
            event.getDamager().damage(character, event.getAmount()/2);
        }));
    }

}
