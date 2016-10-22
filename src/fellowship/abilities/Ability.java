package fellowship.abilities;


import fellowship.characters.BaseCharacter;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.function.Consumer;

public interface Ability extends AbilityInterface {
    void apply(BaseCharacter character);
    default int getNumSlots(){
        return 1;
    }
    default boolean repeatable() {return false;}

    static void addCooldown(int cooldown, BaseCharacter character, Events eventType, Consumer<Event> consumer){
        character.on(eventType, Event.once(event -> {
            consumer.accept(event);
            character.on(Events.TurnStart, Event.after(cooldown, i -> addCooldown(cooldown, character, eventType, consumer)));
        }));
    }

    default String name(){
        return this.getClass().getName();
    }
    default Class<? extends Ability> abilityClass(){
        return getClass();
    }
}
