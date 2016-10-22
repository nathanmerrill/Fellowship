package fellowship.abilities.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Event;
import fellowship.events.Events;

public class Cold implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.enemyCharacters(1).forEach(enemy -> {
            enemy.slow(10);
            character.on(Events.TurnStart, Event.after(1, e -> enemy.slow(-10)));
        });
    }

}
