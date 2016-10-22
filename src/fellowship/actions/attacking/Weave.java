package fellowship.actions.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.actions.CharacterAction;

public class Weave extends CharacterAction{

    public Weave(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.enemyCharacters()
                .forEach(character::slice);
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public int getManaCost() {
        return 15;
    }


}
