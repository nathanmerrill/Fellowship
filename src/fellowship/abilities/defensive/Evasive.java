package fellowship.abilities.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.Random;

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
