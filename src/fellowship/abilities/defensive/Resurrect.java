package fellowship.abilities.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.events.Events;

public class Resurrect implements CharacterAbility {

    @Override
    public void apply(BaseCharacter character) {
        CharacterAbility.addCooldown(40, character, Events.Death, event -> {
            event.cancel();
            character.heal(character.getMaxHealth());
        });
    }

}
