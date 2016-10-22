package fellowship.abilities.vision;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;
import fellowship.Range;

public class Darkness implements CharacterAbility {

    @Override
    public void apply(BaseCharacter character) {
        character.getTeam().getEnemyTeam().getCharacters().forEach(c -> c.setSightRange(new Range(Math.max(1, c.getSightRange().getRange()-1))));
    }

}
