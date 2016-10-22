package fellowship.actions.vision;

import fellowship.Character;
import fellowship.actions.CharacterAction;
import fellowship.actions.TargettedAction;
import fellowship.events.DamageEvent;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.List;

public class Track extends TargettedAction {

    public Track(Character character){
        super(character);
    }

    @Override
    protected List<Character> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public void perform(Character target) {
        int revealEvent = target.on(Events.TurnEnd, Event.forever(t -> target.reveal()));
        int damageEvent = target.on(Events.Damaged, Event.forever(t -> {
            DamageEvent damage = (DamageEvent) t;
            damage.setAmount(damage.getAmount()*.1);
        }));
        character.on(Events.TurnStart, Event.after(10, t -> {
            target.off(revealEvent);
            target.off(damageEvent);
        }));
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public int getManaCost() {
        return 5;
    }
}
