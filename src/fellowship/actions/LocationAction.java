package fellowship.actions;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.Range;

public abstract class LocationAction extends CharacterAction{

    private final Range range;


    public LocationAction(BaseCharacter character, Range range){
        super(character);
        this.range = range;
    }

    public LocationAction(BaseCharacter character, int range){
        this(character, new Range(range));
    }

    public LocationAction(BaseCharacter character){
        this(character, 100);
    }


    public Range getRange(){
        return range;
    }

    @Override
    public final void perform() {
        Point2D location = character.getLocationFor(this, getRange());
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
