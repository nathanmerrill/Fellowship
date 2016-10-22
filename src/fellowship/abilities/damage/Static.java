package fellowship.abilities.damage;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Range;
import fellowship.events.DamageEvent;
import fellowship.events.Event;
import fellowship.events.Events;

public class Static implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.on(Events.TurnStart, Event.forever(i -> {
            character.enemyCharacters(1).forEach(c -> c.damage(5));
        }));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
