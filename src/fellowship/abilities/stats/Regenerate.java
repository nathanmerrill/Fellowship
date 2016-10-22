package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.Stat;
import fellowship.events.Event;
import fellowship.events.Events;

public class Regenerate implements CharacterAbility {

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
