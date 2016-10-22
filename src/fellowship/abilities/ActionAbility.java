package fellowship.abilities;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.actions.CharacterAction;

import java.util.function.Function;

public class ActionAbility implements CharacterAbility {
    private final Function<Character, CharacterAction> action;
    private final boolean repeatable;
    private final int numSlots;
    public ActionAbility(Function<Character, CharacterAction> action, boolean repeatable, int numSlots){
        this.action = action;
        this.repeatable = repeatable;
        this.numSlots = numSlots;
    }
    public ActionAbility(Function<Character, CharacterAction> action, boolean repeatable){
        this(action, repeatable, 1);
    }
    public ActionAbility(Function<Character, CharacterAction> action, int numSlots){
        this(action, false, numSlots);
    }
    public ActionAbility(Function<Character, CharacterAction> action){
        this(action, false, 1);
    }
    @Override
    public void apply(Character character) {
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
