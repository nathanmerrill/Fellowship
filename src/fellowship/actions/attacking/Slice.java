package fellowship.actions.attacking;

import fellowship.Range;
import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;

public class Slice extends TargettedAction {

    public Slice(BaseCharacter character){
        super(character);
    }

    @Override
    public Range getRange() {
        return character.getSliceRange();
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
