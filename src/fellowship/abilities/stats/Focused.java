package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.Stat;
import fellowship.events.Event;
import fellowship.events.Events;

public class Focused implements CharacterAbility {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart, Event.forever(i -> character.addMana(character.getStat(Stat.INT)* BaseCharacter.MANA_REGEN_PER_INT)));
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
