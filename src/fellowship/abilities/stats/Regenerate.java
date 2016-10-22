package fellowship.abilities.stats;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Stat;
import fellowship.events.Event;
import fellowship.events.Events;

public class Regenerate implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.on(Events.TurnStart, Event.forever(i -> {
            character.heal(character.getStat(Stat.STR)/2.0);
        }));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
