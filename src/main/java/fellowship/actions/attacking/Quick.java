package fellowship.actions.attacking;


import fellowship.Range;
import fellowship.actions.TargettedAction;
import fellowship.characters.BaseCharacter;

public class Quick extends TargettedAction {

    public Quick(BaseCharacter character){
        super(character);
    }

    @Override
    public Range getRange() {
        return character.getSliceRange();
    }

    @Override
    public void perform(BaseCharacter target) {
        character.slice(target);
        character.slice(target);
    }

    @Override
    public String toString() {
        return "Double Slap";
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 3;
    }
}
