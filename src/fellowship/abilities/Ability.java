package fellowship.abilities;


import fellowship.characters.BaseCharacter;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.function.Consumer;

public abstract class Ability {
    private final ReadonlyAbility readonly;
    public Ability(){
        this.readonly = new ReadonlyAbility(this);
    }
    public abstract void apply(BaseCharacter character);
    public int getNumSlots(){
        return 1;
    }
    public boolean repeatable() {return false;}

    public static void addCooldown(int cooldown, BaseCharacter character, Events eventType, Consumer<Event> consumer){
        character.on(eventType, Event.once(event -> {
            consumer.accept(event);
            character.on(Events.TurnStart, Event.after(cooldown, i -> addCooldown(cooldown, character, eventType, consumer)));
        }));
    }

    public int attributePoints(){
        return 0;
    }

    public ReadonlyAbility readonly() {
        return readonly;
    }

    public String getName(){
        return getClass().getName();
    }
}
