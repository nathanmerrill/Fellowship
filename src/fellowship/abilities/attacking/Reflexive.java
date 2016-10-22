package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class Reflexive implements CharacterAbility{
    @Override
    public void apply(BaseCharacter character) {
        CharacterAbility.addCooldown(3, character, Events.Death, event -> {
            character.slice(((SliceEvent)event).getSlicer());
        });
    }
}
