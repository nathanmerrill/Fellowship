package fellowship.actions.vision;


import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;

public class Cloak extends Action {

    public Cloak(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.teammates().forEach(teammate -> teammate.invisible(5));
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public int getManaCost() {
        return 20;
    }
}
