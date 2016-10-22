package fellowship.abilities.attacking;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Stat;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class Absorb implements CharacterAbility{

    @Override
    public void apply(Character character) {
        character.on(Events.Slice, Event.forever(i -> {
            SliceEvent event = (SliceEvent)i;
            Character target = event.getSliced();
            Stat primaryStat = target.primaryStat();
            target.addStat(primaryStat, -1);
            character.addStat(primaryStat, 1);
            character.on(Events.TurnStart, Event.after(20, e -> {
                target.addStat(primaryStat, 1);
                character.addStat(primaryStat, -1);
            }));
        }));
    }
}
