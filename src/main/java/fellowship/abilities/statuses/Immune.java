package fellowship.abilities.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Immune extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart, Event.forever(i -> character.dispel()));
    }

}
