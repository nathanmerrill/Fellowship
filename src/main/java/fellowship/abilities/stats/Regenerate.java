package fellowship.abilities.stats;

import fellowship.Stat;
import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;

public class Regenerate extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.addHealthRegen(character.getStat(Stat.STR)* BaseCharacter.HEALTH_REGEN_PER_STR);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
