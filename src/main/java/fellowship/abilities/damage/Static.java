package fellowship.abilities.damage;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Static extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart, Event.forever(i -> {
            character.visibleEnemies(1).forEach(c -> c.damage(5));
        }));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
