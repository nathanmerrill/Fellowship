package fellowship.actions.stats;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.Stat;
import fellowship.events.Event;
import fellowship.events.Events;


public class Werewolf extends CharacterAction {

    public Werewolf(Character character) {
        super(character);
    }

    @Override
    public void perform() {
        for (Stat.StatType type : Stat.StatType.values()) {
            character.addStat(new Stat(type, 10));
        }
        character.on(Events.TurnStart, Event.after(10, t -> {
                    for (Stat.StatType type : Stat.StatType.values()) {
                        character.addStat(new Stat(type, -10));
                    }
                }
        ));
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public int getCooldown() {
        return 25;
    }
}
