package fellowship.abilities.defensive;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.Random;

public class Resurrect implements CharacterAbility {

    @Override
    public void apply(Character character) {
        CharacterAbility.addCooldown(40, character, Events.Death, event -> {
            event.cancel();
            character.heal(character.getMaxHealth());
        });
    }

}
