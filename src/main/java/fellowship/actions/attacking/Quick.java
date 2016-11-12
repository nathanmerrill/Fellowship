package fellowship.actions.attacking;


import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;

public class Quick extends Action {

    public Quick(BaseCharacter character){
        super(character);
    }
    @Override
    public void perform() {
        character.slice();
        character.slice();
    }

    @Override
    public String toString() {
        return "Double Slap";
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 3;
    }
}
