package fellowship.actions.defensive;


import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class ForceField extends Action {

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
