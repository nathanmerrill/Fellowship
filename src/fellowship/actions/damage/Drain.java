package fellowship.actions.damage;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import fellowship.events.Events;
import org.eclipse.collections.api.set.MutableSet;

public class Drain extends TargettedAction {

    public Drain(BaseCharacter character){
        super(character);
    }

    @Override
    protected MutableSet<BaseCharacter> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public void perform(BaseCharacter target) {
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
