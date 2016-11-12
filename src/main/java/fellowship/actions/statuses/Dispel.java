package fellowship.actions.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;

public class Dispel extends TargettedAction {

    public Dispel(BaseCharacter character){
        super(character);
    }

    @Override
    public boolean canTargetTeam() {
        return true;
    }

    @Override
    public void perform(BaseCharacter target) {
        target.dispel();
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public int getManaCost() {
        return 20;
    }
}
