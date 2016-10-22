package fellowship.abilities.defensive;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;

public class Pillar implements CharacterAbility{
    @Override
    public void apply(Character character) {
        CharacterAbility.addCooldown(1, character, Events.Sliced, Event::cancel);
    }

}
