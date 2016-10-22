package fellowship.abilities.stats;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Stat;
import fellowship.events.Event;
import fellowship.events.Events;

public class Buff implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.setMaxHealth(character.getMaxHealth()*2);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
