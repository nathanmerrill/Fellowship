package fellowship.actions.other;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class Smile extends CharacterAction {

    public Smile(Character character){
        super(character);
    }

    @Override
    public void perform() {
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public boolean movementAction() {
        return false;
    }

    @Override
    public boolean basicAction() {
        return true;
    }

    @Override
    public boolean breaksInvisibility() {
        return false;
    }
}
