package fellowship.actions.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Duel extends TargettedAction {

    public Duel(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform(BaseCharacter target) {
        target.freeze(10000000);
        character.freeze(10000000);
        character.on(Events.Death, Event.once(d -> target.dispel()));
        target.on(Events.Death, Event.once(d -> character.dispel()));
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 25;
    }
}
