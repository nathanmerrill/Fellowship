package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.Stat;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

public class Absorb extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Slice, Event.forever(i -> {
            SliceEvent event = (SliceEvent)i;
            BaseCharacter target = event.getSliced();
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
