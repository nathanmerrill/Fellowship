package fellowship.actions;

import fellowship.characters.BaseCharacter;
import org.eclipse.collections.api.set.MutableSet;

public abstract class TargettedAction extends Action {

    public TargettedAction(BaseCharacter character){
        super(character);
    }

    @Override
    public final void perform() {
        MutableSet<BaseCharacter> availableTargets = getAvailableTargets();
        BaseCharacter target = character.getTargetFor(this, availableTargets);
        if (target != null){
            perform(character);
        }
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable()
                && !getAvailableTargets().isEmpty();
    }

    protected abstract MutableSet<BaseCharacter> getAvailableTargets();

    protected abstract void perform(BaseCharacter target);
}
