package fellowship.abilities;


import fellowship.characters.BaseCharacter;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

import java.util.function.Consumer;

public abstract class Ability {
    private final ReadonlyAbility readonly;
    private int remaining;
    public Ability(){
        this.readonly = new ReadonlyAbility(this);
        remaining = 0;
    }
    public abstract void apply(BaseCharacter character);
    public int getNumSlots(){
        return 1;
    }
    public boolean repeatable() {return false;}

    public void addCooldown(int cooldown, BaseCharacter character, Events eventType, Consumer<Event> consumer){
        character.on(eventType, Event.once(event -> {
            consumer.accept(event);
            remaining = cooldown;
            character.on(Events.TurnStart, e -> {
                remaining--;
                if (remaining == 0){
                    addCooldown(cooldown, character, eventType, consumer);
                    return false;
                }
                return true;
            });
        }));
    }

    public int getRemaining() {
        return remaining;
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
