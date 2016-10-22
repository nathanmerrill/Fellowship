package fellowship.abilities.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Events;

public class Resurrect implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        Ability.addCooldown(40, character, Events.Death, event -> {
            event.cancel();
            character.heal(character.getMaxHealth());
        });
    }

}
