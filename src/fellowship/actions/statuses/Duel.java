package fellowship.actions.statuses;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.List;

public class Duel extends TargettedAction {

    public Duel(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters(1);
    }

    @Override
    public void perform(Character target) {
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
