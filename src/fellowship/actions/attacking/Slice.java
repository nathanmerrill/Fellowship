package fellowship.actions.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import org.eclipse.collections.api.set.MutableSet;

public class Slice extends TargettedAction {

    public Slice(BaseCharacter character){
        super(character);
    }

    @Override
    protected MutableSet<BaseCharacter> getAvailableTargets() {
        return character.enemyCharacters(character.getSliceRange());
    }

    @Override
    public void perform(BaseCharacter target) {
        character.slice(target);
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public boolean basicAction() {
        return true;
    }
}
