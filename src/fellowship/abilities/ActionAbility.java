package fellowship.abilities;

import fellowship.actions.Action;
import fellowship.characters.BaseCharacter;
import org.eclipse.collections.api.block.function.Function;


public class ActionAbility implements Ability {
    private final Function<BaseCharacter, Action> action;
    private final boolean repeatable;
    private final int numSlots;
    public ActionAbility(Function<BaseCharacter, Action> action, boolean repeatable, int numSlots){
        this.action = action;
        this.repeatable = repeatable;
        this.numSlots = numSlots;
    }
    public ActionAbility(Function<BaseCharacter, Action> action, boolean repeatable){
        this(action, repeatable, 1);
    }
    public ActionAbility(Function<BaseCharacter, Action> action, int numSlots){
        this(action, false, numSlots);
    }
    public ActionAbility(Function<BaseCharacter, Action> action){
        this(action, false, 1);
    }
    @Override
    public void apply(BaseCharacter character) {
        character.addAction(action.apply(character));
    }

    @Override
    public boolean repeatable() {
        return repeatable;
    }

    @Override
    public int getNumSlots() {
        return numSlots;
    }
}
