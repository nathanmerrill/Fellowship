package fellowship.actions.vision;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import fellowship.events.DamageEvent;
import fellowship.events.Event;
import fellowship.events.Events;
import org.eclipse.collections.api.set.MutableSet;

public class Track extends TargettedAction {

    public Track(BaseCharacter character){
        super(character);
    }

    @Override
    protected MutableSet<BaseCharacter> getAvailableTargets() {
        return character.enemyCharacters();
    }

    @Override
    public void perform(BaseCharacter target) {
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
