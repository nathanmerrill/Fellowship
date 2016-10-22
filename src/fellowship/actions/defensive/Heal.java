package fellowship.actions.defensive;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import org.eclipse.collections.api.set.MutableSet;

public class Heal extends TargettedAction {

    public Heal(BaseCharacter character){
        super(character);
    }

    @Override
    protected MutableSet<BaseCharacter> getAvailableTargets() {
        return character.teamCharacters();
    }

    @Override
    public void perform(BaseCharacter target) {
        target.heal(20);
    }

    @Override
    public int getCooldown() {
        return 3;
    }

    @Override
    public int getManaCost() {
        return 10;
    }
}
