package fellowship.actions.vision;


import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;

public class Hide extends Action {

    public Hide(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.invisible(5);
    }

    @Override
    public int getCooldown() {
        return 7;
    }

    @Override
    public int getManaCost() {
        return 4;
    }
}
