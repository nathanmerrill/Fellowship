package fellowship.actions.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import fellowship.events.Event;
import fellowship.events.Events;

public class Slow extends TargettedAction {

    public Slow(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform(BaseCharacter target) {
        target.slow(40);
        target.on(Events.TurnStart, Event.after(3, e -> target.slow(-40)));
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public int getManaCost() {
        return 10;
    }
}
