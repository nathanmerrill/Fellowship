package fellowship.abilities.statuses;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;

public class Cold implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.enemyCharacters(1).forEach(enemy -> {
            enemy.slow(10);
            character.on(Events.TurnStart, Event.after(1, e -> enemy.slow(-10)));
        });
    }

}
