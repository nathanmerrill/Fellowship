package fellowship.abilities;

import fellowship.actions.Action;
import fellowship.actions.other.Clone;
import fellowship.characters.BaseCharacter;
import org.eclipse.collections.api.block.function.Function;


public class ActionAbility extends Ability {
    private final Function<BaseCharacter, Action> action;
    public ActionAbility(Function<BaseCharacter, Action> action){
        this.action = action;
    }
    @Override
    public void apply(BaseCharacter character) {
        character.addAction(action.apply(character));
    }

    @Override
    public boolean repeatable() {
        return true;
    }

    public Class<? extends Action> actionClass(){
        return sampleInstance().getClass();
    }

    private Action sampleInstance(){
        return action.apply(null);
    }

    @Override
    public int getNumSlots() {
        if (sampleInstance() instanceof Clone) {
            return 2;
        }
        return 1;
    }

    public String getName(){
        return "Ability "+sampleInstance().getName();
    }
}
