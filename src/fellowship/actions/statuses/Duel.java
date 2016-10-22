package fellowship.actions.statuses;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import fellowship.events.Event;
import fellowship.events.Events;
import org.eclipse.collections.api.set.MutableSet;

public class Duel extends TargettedAction {

    public Duel(BaseCharacter character){
        super(character);
    }

    @Override
    protected MutableSet<BaseCharacter> getAvailableTargets() {
        return character.enemyCharacters(1);
    }

    @Override
    public void perform(BaseCharacter target) {
        target.freeze(10000000);
        character.freeze(10000000);
        character.on(Events.Death, Event.once(d -> target.dispel()));
        target.on(Events.Death, Event.once(d -> character.dispel()));
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 25;
    }
}
