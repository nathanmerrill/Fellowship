package fellowship.actions.other;

import com.ppcg.kothcomm.game.maps.gridmaps.Point2D;
import fellowship.Character;
import fellowship.actions.LocationAction;

public class Clone extends LocationAction {

    public Clone(Character character){
        super(character, 6);
    }
    @Override
    public void perform(Point2D location) {
        Character clone = new Character();
        character.getStats().forEach(clone::addStat);
        character.getAbilities().forEach(clone::addAbility);
        clone.removeMana(character.getMaxMana()-character.getMana());
        clone.damage(character.getMaxHealth()-character.getHealth());
        character.getTeam().addCharacter(clone);
        character.getMap().put(location, clone);
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
