package fellowship.abilities.vision;

import fellowship.Character;
import fellowship.CharacterAbility;
import fellowship.Range;

public class Darkness implements CharacterAbility {

    @Override
    public void apply(Character character) {
        character.getTeam().getEnemyTeam().getPlayers().forEach(c -> c.setSightRange(new Range(Math.max(1, c.getSightRange().getRange()-1))));
    }

}
