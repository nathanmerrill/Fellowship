package fellowship.actions.defensive;


import fellowship.actions.Action;
import fellowship.characters.BaseCharacter;
import fellowship.events.Events;

public class ForceField extends Action {

    private int remaining = 0;
    public ForceField(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        remaining = 5;
        character.on(Events.Damaged, e ->{
            remaining--;
            if (remaining == 0) {
                return false;
            }
            return true;
        });
    }

    @Override
    public int getRemaining() {
        return remaining;
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
