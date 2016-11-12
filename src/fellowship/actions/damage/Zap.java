package fellowship.actions.damage;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;

public class Zap extends TargettedAction{

    public Zap(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform(BaseCharacter target) {
        target.damage(character, 30);
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
