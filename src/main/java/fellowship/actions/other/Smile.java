package fellowship.actions.other;


import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;

public class Smile extends Action {

    public Smile(BaseCharacter character){
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

    @Override
    public boolean isAvailable() {
        return true;
    }
}
