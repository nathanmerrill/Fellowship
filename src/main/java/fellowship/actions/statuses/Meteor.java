package fellowship.actions.statuses;

import fellowship.actions.Action;
import fellowship.characters.BaseCharacter;

public class Meteor extends Action {

    public Meteor(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.visibleEnemies().forEachWith(BaseCharacter::stun, 100);
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public int getManaCost() {
        return 25;
    }
}
