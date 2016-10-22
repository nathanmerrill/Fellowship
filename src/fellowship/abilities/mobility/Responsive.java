package fellowship.abilities.mobility;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Range;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class Responsive implements CharacterAbility{
    @Override
    public void apply(Character character) {
        CharacterAbility.addCooldown(3, character, Events.Death, event -> {
            character.step();
        });
    }
}
