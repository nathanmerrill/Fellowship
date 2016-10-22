package fellowship.abilities.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;

public class Pillar implements CharacterAbility{
    @Override
    public void apply(BaseCharacter character) {
        CharacterAbility.addCooldown(1, character, Events.Sliced, Event::cancel);
    }

}
