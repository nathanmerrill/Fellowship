package fellowship.characters;


import com.ppcg.kothcomm.game.maps.gridmaps.GridMap;
import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.ActionQueue;
import fellowship.MapObject;
import fellowship.teams.Team;

public class BearCharacter extends BaseCharacter {
    public BearCharacter(ActionQueue actionQueue, GridMap<Point2D, MapObject> map, Team team){
        super(actionQueue, map, team);
    }
}
