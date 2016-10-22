package fellowship.abilities.statuses;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.actions.TargettedAction;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.ArrayList;
import java.util.List;

public class Immune implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.on(Events.TurnStart, Event.forever(i -> character.dispel()));
    }

}
