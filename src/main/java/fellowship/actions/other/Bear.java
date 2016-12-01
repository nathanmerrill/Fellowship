package fellowship.actions.other;

import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.Stat;
import fellowship.abilities.vision.FarSight;
import fellowship.actions.LocationAction;
import fellowship.characters.BaseCharacter;

public class Bear extends LocationAction {

    public Bear(BaseCharacter character){
        super(character, 6);
    }

    @Override
    public void perform(Point2D location) {
        BaseCharacter bear = new BaseCharacter(character.getActionQueue(), character.getMap(), character.getTeam(), character.getRandom());
        for (Stat stat: Stat.values()){
            bear.addStat(stat, 5);
        }
        bear.setSightRange(character.getSightRange()
                .shorter(2*character.getAbilities().count(c -> c.getClass().equals(FarSight.class))));
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
