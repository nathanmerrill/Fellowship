package fellowship.abilities.mobility;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.events.Events;

public class Responsive implements CharacterAbility{
    @Override
    public void apply(BaseCharacter character) {
        CharacterAbility.addCooldown(3, character, Events.Death, event -> {
            character.step();
        });
    }
}
