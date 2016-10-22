package fellowship.abilities.attacking;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

import java.util.function.Consumer;

public class Swipe implements Ability {
    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.Slice, Event.forever(new Consumer<Event>(){
            BaseCharacter lastTarget = null;
            int additional = 0;
            @Override
            public void accept(Event event) {
                SliceEvent slice = (SliceEvent)event;
                BaseCharacter target = slice.getSlicer();
                if (target.equals(lastTarget)){
                    additional += 3;
                    slice.setAmount(additional+slice.getAmount());
                } else {
                    lastTarget = target;
                    additional = 0;
                }
            }
        }));
    }
}
