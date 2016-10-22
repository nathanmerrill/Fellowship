package fellowship.abilities.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;

public class Immune implements CharacterAbility {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart, Event.forever(i -> character.dispel()));
    }

}
