package fellowship.actions.damage;

import fellowship.Character;
import fellowship.actions.TargettedAction;

import java.util.List;

public class KO extends TargettedAction {
    public final static double KO_LIMIT = .2;

    public KO(Character character){
        super(character);
    }
    @Override
    public void perform(Character target) {
        if (target.getHealth() <= target.getMaxHealth()*KO_LIMIT){
            target.damage(character, target.getHealth());
        }
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 20;
    }
}
