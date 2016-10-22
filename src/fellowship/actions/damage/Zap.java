package fellowship.actions.damage;

import fellowship.Character;
import fellowship.actions.TargettedAction;

import java.util.List;

public class Zap extends TargettedAction{

    public Zap(Character character){
        super(character);
    }

    @Override
    public void perform(Character target) {
        target.damage(character, 30);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public int getManaCost() {
        return 15;
    }
}
