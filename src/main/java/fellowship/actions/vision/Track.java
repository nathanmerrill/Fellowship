package fellowship.actions.vision;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import fellowship.events.DamageEvent;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.events.Events;

public class Track extends TargettedAction {

    public Track(BaseCharacter character){
        super(character);
    }

    @Override
    public boolean canTargetEnemies() {
        return true;
    }

    @Override
    public void perform(BaseCharacter target) {
        int revealEvent = target.on(Events.TurnEnd, Event.forever(t -> target.reveal()));
        int damageEvent = target.on(Events.Damaged, Event.forever(t -> {
            DamageEvent damage = (DamageEvent) t;
            damage.setAmount(damage.getAmount()*1.1);
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
