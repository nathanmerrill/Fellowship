package fellowship.abilities.vision;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Invisible extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart, Event.forever(i -> {
            if (character.isInvisible()){
                return;
            }
            if (!character.getTeam().getEnemyTeam().getCharacters().get(0).visibleEnemies().contains(character)){
                character.invisible(1000000);
            }
        }));
    }

}
