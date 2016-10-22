package fellowship.abilities.attacking;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.events.Event;
import fellowship.events.Events;
import fellowship.events.SliceEvent;

import java.util.function.Consumer;

public class Swipe implements CharacterAbility{
    @Override
    public void apply(Character character) {
        character.on(Events.Slice, Event.forever(new Consumer<Event>(){
            Character lastTarget = null;
            int additional = 0;
            @Override
            public void accept(Event event) {
                SliceEvent slice = (SliceEvent)event;
                Character target = slice.getSlicer();
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
