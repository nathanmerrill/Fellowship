package fellowship.abilities.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Evasive extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Sliced, Event.forever(i -> {
            if (character.getRandom().nextDouble() < .25){
                i.cancel();
            }
        }));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
