package fellowship.abilities.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Event;
import fellowship.events.Events;

public class Pillar implements Ability {
    @Override
    public void apply(BaseCharacter character) {
        Ability.addCooldown(1, character, Events.Sliced, Event::cancel);
    }

}
