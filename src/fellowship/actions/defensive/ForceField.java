package fellowship.actions.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.actions.CharacterAction;
import fellowship.events.Event;
import fellowship.events.Events;

public class ForceField extends CharacterAction {

    public ForceField(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.on(Events.Damaged, Event.every(5, Event::cancel));
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public int getManaCost() {
        return 15;
    }
}
