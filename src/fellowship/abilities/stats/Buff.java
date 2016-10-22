package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.CharacterAbility;

public class Buff implements CharacterAbility {

    @Override
    public void apply(BaseCharacter character) {
        character.setMaxHealth(character.getMaxHealth()*2);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
