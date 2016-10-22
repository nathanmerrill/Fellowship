package fellowship.actions;

import fellowship.Character;

import java.util.List;

public abstract class TargettedAction extends CharacterAction{

    public TargettedAction(Character character){
        super(character);
    }

    @Override
    public final void perform() {
        List<Character> availableTargets = getAvailableTargets();
        Character target = character.getTargetFor(this, availableTargets);
        if (target != null){
            perform(character);
        }
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable()
                && !getAvailableTargets().isEmpty();
    }

    protected abstract List<Character> getAvailableTargets();

    protected abstract void perform(Character target);
}
