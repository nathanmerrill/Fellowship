package fellowship.actions.damage;

import fellowship.Character;
import fellowship.actions.TargettedAction;
import fellowship.events.Events;

import java.util.List;

public class Drain extends TargettedAction {

    public Drain(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public void perform(Character target) {
        target.damage(5);
        character.heal(5);
        character.on(Events.TurnStart, t -> {
            if (character.enemyCharacters(1).contains(target)){
                target.damage(character, 5);
                character.heal(5);
                return true;
            }
            return false;
        });

    }


    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public int getManaCost() {
        return 11;
    }
}
