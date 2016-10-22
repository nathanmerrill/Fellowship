package fellowship.abilities.defensive;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.Random;

public class Evasive implements CharacterAbility {

    private final Random random;
    public Evasive(Random random){
        this.random = random;
    }
    @Override
    public void apply(Character character) {
        character.on(Events.Sliced, Event.forever(i -> {
            if (random.nextDouble() < .25){
                i.cancel();
            }
        }));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
