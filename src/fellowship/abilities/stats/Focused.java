package fellowship.abilities.stats;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Stat;
import fellowship.events.Event;
import fellowship.events.Events;

public class Focused implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.on(Events.TurnStart, Event.forever(i -> character.removeMana(-character.getStat(Stat.INT)/5.0)));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
