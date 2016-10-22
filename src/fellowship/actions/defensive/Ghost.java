package fellowship.actions.defensive;

import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.actions.CharacterAction;
import fellowship.events.DamageEvent;

import fellowship.characters.BaseCharacter;


public class Ghost extends CharacterAction {

    public Ghost(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {

        int id = character.on(Events.Damaged, Event.forever((Event event) -> {
            DamageEvent damaged = (DamageEvent) event;
            damaged.cancel();
            character.heal(damaged.getAmount());
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
