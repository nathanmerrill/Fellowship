package fellowship.actions.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;

public class Heal extends TargettedAction {

    public Heal(BaseCharacter character){
        super(character);
    }

    @Override
    public boolean canTargetEnemies() {
        return false;
    }

    @Override
    public boolean canTargetTeam() {
        return true;
    }

    @Override
    public void perform(BaseCharacter target) {
        target.heal(20);
    }

    @Override
    public int getCooldown() {
        return 3;
    }

    @Override
    public int getManaCost() {
        return 10;
    }
}
