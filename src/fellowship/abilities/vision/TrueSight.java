package fellowship.abilities.vision;

import com.ppcg.kothcomm.game.maps.gridmaps.GridMap;
import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.*;
import fellowship.Character;
import fellowship.events.Event;
import fellowship.events.Events;

public class TrueSight implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.on(Events.TurnStart, Event.forever(i -> {
            GridMap<Point2D, MapObject> map = character.getMap();
            Team enemy = character.getTeam().getEnemyTeam();
            character.rangeAround(new Range(2))
                    .stream()
                    .filter(map::isFilled)
                    .map(map::get)
                    .forEach(c -> {
                        if (c instanceof Character) {
                            Character ch = (Character) c;
                            if (enemy.contains(ch)){
                                ch.reveal();
                            }
                        }
                    });
        }));
    }

}
