package fellowship.actions.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import org.eclipse.collections.api.set.MutableSet;

public class Leash extends TargettedAction {

    public Leash(BaseCharacter character){
        super(character);
    }

    @Override
    protected MutableSet<BaseCharacter> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public void perform(BaseCharacter target) {
        target.freeze(2);
    }

    @Override
    public int getCooldown() {
        return 6;
    }

    @Override
    public int getManaCost() {
        return 4;
    }
}
