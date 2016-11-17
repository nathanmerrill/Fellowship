package fellowship.abilities.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.DamageEvent;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Spikes extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Damaged, Event.forever(i -> {
            DamageEvent event = (DamageEvent)i;
            if (event.getAmount() < .5){
                return;
            }
            event.getDamager().damage(character, event.getAmount()/2);
        }));
    }

}
