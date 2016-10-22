package fellowship.actions.other;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.*;
import fellowship.characters.BearCharacter;
import fellowship.characters.BaseCharacter;
import fellowship.actions.LocationAction;
import fellowship.actions.mobility.Step;

public class Bear extends LocationAction {

    public Bear(BaseCharacter character){
        super(character, 6);
    }

    @Override
    public void perform(Point2D location) {
        BearCharacter bear = new BearCharacter(character.getActionQueue(), character.getMap(), character.getTeam());
        for (Stat stat: Stat.values()){
            bear.addStat(stat, 5);
        }
        bear.addAction(new Step(bear));
        bear.setLocation(location);
        bear.start();
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
