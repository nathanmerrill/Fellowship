package fellowship.actions.other;

import com.nmerrill.kothcomm.game.maps.Point2D;
import fellowship.characters.BaseCharacter;
import fellowship.Stat;
import fellowship.actions.LocationAction;

public class Clone extends LocationAction {

    public Clone(BaseCharacter character){
        super(character, 6);
    }
    @Override
    public void perform(Point2D location) {
        BaseCharacter clone = new BaseCharacter(character.getActionQueue(), character.getMap(), character.getTeam(), character.getRandom());
        for (Stat stat: Stat.values()){
            clone.addStat(stat, character.getStat(stat));
        }
        character.getAbilities().forEach(clone::addAbility);
        clone.removeMana(character.getMaxMana()-character.getMana());
        clone.damage(character.getMaxHealth()-character.getHealth());
        clone.setLocation(location);
        character.getTeam().addCharacter(clone);
        clone.start();

    }

    @Override
    public int getCooldown() {
        return 100;
    }

    @Override
    public int getManaCost() {
        return 100;
    }
}
