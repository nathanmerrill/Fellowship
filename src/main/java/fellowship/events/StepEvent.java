package fellowship.events;

import com.nmerrill.kothcomm.game.maps.Point2D;
import com.nmerrill.kothcomm.utils.Event;
import fellowship.characters.BaseCharacter;

public class StepEvent extends Event {
    private final BaseCharacter character;
    private final Point2D location;

    public StepEvent(BaseCharacter character, Point2D location){
        this.character = character;
        this.location = location;
    }

    public BaseCharacter getCharacter(){
        return character;
    }

    public Point2D getLocation(){
        return location;
    }
}
