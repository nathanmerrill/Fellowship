package fellowship.actions.statuses;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.List;

public class Slow extends TargettedAction {

    public Slow(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public void perform(Character target) {
        target.slow(40);
        target.on(Events.TurnStart, Event.after(3, e -> target.slow(-40)));
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public int getManaCost() {
        return 10;
    }
}
