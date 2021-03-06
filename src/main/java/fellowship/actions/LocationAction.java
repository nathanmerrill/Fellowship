package fellowship.actions;

import com.nmerrill.kothcomm.game.maps.Point2D;
import com.nmerrill.kothcomm.game.maps.graphmaps.GraphMap;
import fellowship.MapObject;
import fellowship.Range;
import fellowship.TrapStack;
import fellowship.characters.BaseCharacter;
import org.eclipse.collections.api.set.MutableSet;

public abstract class LocationAction extends Action {

    private final Range range;
    private Point2D location;

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

    @Override
    public Range getRange(){
        return range;
    }

    @Override
    public MutableSet<Point2D> availableLocations(){
        return character.rangeAround(getRange()).select(this::validLocation);
    }

    @Override
    public final void perform() {
        if (location == null){
            throw new RuntimeException("Location not set");
        }
        if (!validLocation(location)){
            return;
        }
        perform(location);
    }

    public boolean validLocation(Point2D location){
        GraphMap<Point2D, MapObject> map = character.getMap();
        return map.isEmpty(location) || map.get(location) instanceof TrapStack;
    }

    @Override
    public boolean needsLocation() {
        return true;
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable()
                && !availableLocations().isEmpty();
    }

    public void setLocation(Point2D location){
        if (availableLocations().contains(location)) {
            this.location = location;
        } else {
            throw new RuntimeException("Invalid location");
        }
    }

    public Point2D getLocation() {
        return location;
    }

    public void clearSelection(){
        this.location = null;
    }


    protected abstract void perform(Point2D location);
}
