package fellowship.abilities.defensive;

import com.nmerrill.kothcomm.utils.Event;
import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;
import fellowship.events.Events;

public class Pillar extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        applyPillar(character);
    }

    private void applyPillar(BaseCharacter character){
        character.on(Events.Sliced, Event.once(e -> {
            int id = character.on(Events.Sliced, Event.forever(Event::cancel));
            character.on(Events.TurnStart, Event.once(f -> {
                character.off(id);
                applyPillar(character);
            }));
        }));
    }

}
