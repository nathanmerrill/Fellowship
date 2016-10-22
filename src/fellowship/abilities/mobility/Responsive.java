package fellowship.abilities.mobility;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Events;

public class Responsive implements Ability {
    @Override
    public void apply(BaseCharacter character) {
        Ability.addCooldown(3, character, Events.Death, event -> {
            character.step();
        });
    }
}
