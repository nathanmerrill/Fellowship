package fellowship;


import fellowship.events.Event;
import fellowship.events.Events;

import java.util.function.Consumer;

public interface CharacterAbility {
    void apply(Character character);
    default int getNumSlots(){
        return 1;
    }
    default boolean repeatable() {return false;}

    static void addCooldown(int cooldown, Character character, Events eventType, Consumer<Event> consumer){
        character.on(eventType, Event.once(event -> {
            consumer.accept(event);
            character.on(Events.TurnStart, Event.after(cooldown, i -> addCooldown(cooldown, character, eventType, consumer)));
        }));
    }
}
