package fellowship.abilities.vision;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;
import fellowship.Range;

public class Darkness implements Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.getTeam().getEnemyTeam().getCharacters().forEach(c -> c.setSightRange(new Range(Math.max(1, c.getSightRange().getRange()-1))));
    }

}
