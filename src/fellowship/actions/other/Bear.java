package fellowship.actions.other;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.*;
import fellowship.Character;
import fellowship.actions.LocationAction;
import fellowship.actions.mobility.Step;

public class Bear extends LocationAction {

    public Bear(Character character){
        super(character, 6);
    }

    @Override
    public void perform(Point2D location) {
        BearCharacter bear = new BearCharacter();
        for (Stat.StatType stat: Stat.StatType.values()){
            bear.addStat(new Stat(stat, 5));
        }
        bear.addAction(new Step(bear));
        character.getTeam().addCharacter(bear);
        character.getMap().put(location, bear);
        //TODO:  add character to time
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public int getManaCost() {
        return 8;
    }
}
