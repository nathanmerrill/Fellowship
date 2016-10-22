package fellowship.abilities.vision;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;

public class Invisible implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.on(Events.TurnStart, Event.forever(i -> {
            if (!character.getTeam().getEnemyTeam().getPlayers().get(0).enemyCharacters().contains(character)){
                character.invisible(1000000);
            }
        }));
    }

}
