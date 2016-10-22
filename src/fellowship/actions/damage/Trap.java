package fellowship.actions.damage;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.events.Events;
import fellowship.actions.LocationAction;
import fellowship.events.StepEvent;

public class Trap extends LocationAction {

    public Trap(BaseCharacter character){
        super(character, 4);
    }

    @Override
    public void perform(Point2D location) {
        character.enemyCharacters().forEach(enemy -> enemy.on(Events.Step, event -> {
            StepEvent step = (StepEvent) event;
            if ((step.getLocation().equals(location))){
                if (character.enemyCharacters().contains(step.getCharacter())){
                    step.getCharacter().damage(20);
                    return false;
                }
            }
            return true;
        }));
    }

    @Override
    public int getCooldown() {
        return 2;
    }

    @Override
    public int getManaCost() {
        return 10;
    }
}
