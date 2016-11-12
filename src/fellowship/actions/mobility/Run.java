package fellowship.actions.mobility;


import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;

public class Run extends Action {

    public Run(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.step();
        character.step();
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 1;
    }

    @Override
    public boolean movementAction() {
        return true;
    }

    @Override
    public boolean basicAction() {
        return true;
    }
}
