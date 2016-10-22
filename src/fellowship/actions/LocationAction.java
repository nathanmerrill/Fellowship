package fellowship.actions;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.Character;

public abstract class LocationAction extends CharacterAction{

    private final int range;

    public LocationAction(Character character, int range){
        super(character);
        this.range = range;
    }

    public LocationAction(Character character){
        this(character, 100);
    }

    @Override
    public final void perform() {
        Point2D location = character.getLocationFor(this, range);
        if (location == null){
            return;
        }
        if (!character.getMap().isEmpty(location)){
            return;
        }
        perform(location);
    }


    protected abstract void perform(Point2D location);
}
