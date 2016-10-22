package fellowship.actions.attacking;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class Quick extends CharacterAction{

    public Quick(Character character){
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
