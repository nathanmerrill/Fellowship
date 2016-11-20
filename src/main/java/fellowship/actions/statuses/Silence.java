package fellowship.actions.statuses;

import fellowship.actions.TargettedAction;
import fellowship.characters.BaseCharacter;

public class Silence extends TargettedAction {

    public Silence(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform(BaseCharacter target) {
        target.silence(5);
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public int getManaCost() {
        return 7;
    }
}
