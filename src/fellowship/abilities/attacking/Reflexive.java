package fellowship.abilities.attacking;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class Reflexive implements CharacterAbility{
    @Override
    public void apply(Character character) {
        CharacterAbility.addCooldown(3, character, Events.Death, event -> {
            character.slice(((SliceEvent)event).getSlicer());
        });
    }
}
