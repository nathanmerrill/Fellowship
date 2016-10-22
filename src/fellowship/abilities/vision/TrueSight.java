package fellowship.abilities.vision;

import com.ppcg.kothcomm.game.maps.gridmaps.GridMap;
import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.*;
import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;
import fellowship.teams.Team;
import fellowship.events.Event;
import fellowship.events.Events;

public class TrueSight implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.on(Events.TurnStart, Event.forever(i -> {
            GridMap<Point2D, MapObject> map = character.getMap();
            Team enemy = character.getTeam().getEnemyTeam();
            character.rangeAround(new Range(2))
                    .stream()
                    .filter(map::isFilled)
                    .map(map::get)
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
