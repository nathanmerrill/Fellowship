package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.Stat;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Focused extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart, Event.forever(i -> character.addMana(character.getStat(Stat.INT)* BaseCharacter.MANA_REGEN_PER_INT)));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
