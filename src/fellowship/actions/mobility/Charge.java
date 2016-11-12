package fellowship.actions.mobility;


import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;

public class Charge extends Action {

    public Charge(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.step();
        character.slice();
    }

    @Override
    public int getManaCost() {
        return 2;
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public boolean movementAction() {
        return true;
    }
}
