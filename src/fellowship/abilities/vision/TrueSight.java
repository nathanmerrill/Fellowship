package fellowship.abilities.vision;

import com.nmerrill.kothcomm.game.maps.graphmaps.GraphMap;
import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.*;
import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;
import fellowship.characters.Team;
import fellowship.events.Event;
import fellowship.events.Events;

public class TrueSight extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart, Event.forever(i -> {
            GraphMap<Point2D, MapObject> map = character.getMap();
            Team enemy = character.getTeam().getEnemyTeam();
            character.rangeAround(new Range(2))
                    .select(map::isFilled)
                    .collect(map::get)
                    .forEach(c -> {
                        if (c instanceof BaseCharacter) {
                            BaseCharacter ch = (BaseCharacter) c;
                            if (enemy.contains(ch)){
                                ch.reveal();
                            }
                        }
                    });
        }));
    }

}
