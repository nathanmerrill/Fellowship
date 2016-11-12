package fellowship.abilities.mobility;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Events;

public class Responsive extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        Ability.addCooldown(3, character, Events.Death, event -> {
            character.step();
        });
    }
}
