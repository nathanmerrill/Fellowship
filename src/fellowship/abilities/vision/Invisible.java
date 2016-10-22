package fellowship.abilities.vision;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Event;
import fellowship.events.Events;

public class Invisible implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart, Event.forever(i -> {
            if (!character.getTeam().getEnemyTeam().getCharacters().get(0).enemyCharacters().contains(character)){
                character.invisible(1000000);
            }
        }));
    }

}
