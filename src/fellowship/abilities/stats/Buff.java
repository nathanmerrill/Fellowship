package fellowship.abilities.stats;

import fellowship.characters.BaseCharacter;
import fellowship.abilities.Ability;

public class Buff extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.setMaxHealth(character.getMaxHealth()*2);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
