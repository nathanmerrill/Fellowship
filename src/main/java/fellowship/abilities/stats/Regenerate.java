package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.Stat;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Regenerate extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart,
                Event.forever(i -> character.heal(character.getStat(Stat.STR)* BaseCharacter.HEALTH_REGEN_PER_STR)));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
