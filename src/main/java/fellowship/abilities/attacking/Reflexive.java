package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class Reflexive extends Ability {
    @Override
    public void apply(BaseCharacter character) {
        this.addCooldown(3, character, Events.Sliced, event -> character.slice(((SliceEvent)event).getSlicer()));
    }
}
