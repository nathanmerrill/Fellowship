package fellowship.actions.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import org.eclipse.collections.api.set.MutableSet;

public class Dispel extends TargettedAction{

    public Dispel(BaseCharacter character){
        super(character);
    }

    @Override
    protected MutableSet<BaseCharacter> getAvailableTargets() {
        return character.enemyCharacters().withAll(character.teamCharacters());
    }

    @Override
    public void perform(BaseCharacter target) {
        target.dispel();
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public int getManaCost() {
        return 20;
    }
}
