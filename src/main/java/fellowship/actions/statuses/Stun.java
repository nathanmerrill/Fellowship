package fellowship.actions.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;

public class Stun extends TargettedAction {

    public Stun(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform(BaseCharacter target) {
        target.stun(300);
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
