package fellowship.actions.defensive;

import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.actions.CharacterAction;
import fellowship.events.DamageEvent;

import fellowship.Character;


public class Ghost extends CharacterAction {

    public Ghost(Character character){
        super(character);
    }

    @Override
    public void perform() {

        int id = character.on(Events.Damage, Event.forever(sliceEvent -> {
            sliceEvent.cancel();
            character.heal(((DamageEvent)sliceEvent).getAmount());
        }));
        character.on(Events.TurnStart, Event.once(i -> character.off(id)));
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public int getManaCost() {
        return 10;
    }
}
