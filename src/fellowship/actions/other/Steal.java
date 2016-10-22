package fellowship.actions.other;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.List;

public class Steal extends TargettedAction {

    public Steal(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters(4);
    }

    @Override
    public void perform(Character target) {
        CharacterAction lastAction = target.getLastAction();
        if (lastAction != null) {
            character.addAction(new CharacterAction(character) {
                @Override
                public void perform() {
                    lastAction.perform();
                }

                @Override
                public int getManaCost() {
                    return lastAction.getManaCost();
                }

                @Override
                public int getCooldown() {
                    return lastAction.getCooldown();
                }
            });
            character.removeAction(this);
            character.on(Events.TurnStart, Event.after(10, t -> {
                        character.removeAction(lastAction);
                        character.addAction(this);
                    }
            ));
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 5;
    }
}
