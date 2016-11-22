package fellowship.abilities.vision;

import com.nmerrill.kothcomm.utils.Event;
import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;
import fellowship.events.Events;

public class Darkness extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.getTeam().getEnemyTeam().getCharacters().forEach(c -> c.setSightRange(c.getSightRange().shorter(1)));
        character.on(Events.Death, Event.forever(e -> {
            if (!e.isCancelled()) {
                character.getTeam().getEnemyTeam().getCharacters().forEach(c -> c.setSightRange(c.getSightRange().longer(1)));
            }
        }));
    }

}
