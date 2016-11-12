package fellowship.actions.defensive;


import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;
import fellowship.actions.Action;
import fellowship.events.DamageEvent;

import fellowship.characters.BaseCharacter;


public class Ghost extends Action {

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
