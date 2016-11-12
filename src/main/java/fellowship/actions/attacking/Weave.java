package fellowship.actions.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;

public class Weave extends Action {

    public Weave(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.visibleEnemies()
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
