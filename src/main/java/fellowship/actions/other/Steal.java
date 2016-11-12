package fellowship.actions.other;

import fellowship.Range;

import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;
import fellowship.actions.TargettedAction;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Steal extends TargettedAction {

    public Steal(BaseCharacter character){
        super(character);
    }

    @Override
    public Range getRange() {
        return new Range(4);
    }

    @Override
    public void perform(BaseCharacter target) {
        Action lastAction = target.getLastAction();
        if (lastAction != null) {
            character.addAction(new Action(character) {
                @Override
                public void perform() {
                    lastAction.perform();
                }

                @Override
                public int getManaCost() {
                    return lastAction.getManaCost();
                }

                @Override
                public int getCooldown() {
                    return lastAction.getCooldown();
                }
            });
            character.removeAction(this);
            character.on(Events.TurnStart, Event.after(10, t -> {
                        character.removeAction(lastAction);
                        character.addAction(this);
                    }
            ));
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 5;
    }
}
