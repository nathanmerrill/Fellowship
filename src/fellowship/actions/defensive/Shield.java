package fellowship.actions.defensive;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.events.Event;
import fellowship.events.Events;

public class Shield extends CharacterAction {
    public Shield(Character character){
        super(character);
    }
    @Override
    public void perform() {
        int id = character.on(Events.Damage, Event.forever(Event::cancel));
        character.on(Events.TurnStart, Event.once(i -> character.off(id)));
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 3;
    }
}
