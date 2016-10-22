package fellowship.actions.statuses;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;

import java.util.ArrayList;
import java.util.List;

public class Dispel extends TargettedAction{

    public Dispel(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        ArrayList<Character> targets = new ArrayList<>(character.enemyCharacters());
        targets.addAll(character.teamCharacters());
        return targets;
    }

    @Override
    public void perform(Character target) {
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
