package fellowship.abilities.stats;

import fellowship.Stat;
import fellowship.abilities.Ability;
import fellowship.characters.BaseCharacter;

public class Focused extends Ability {

    @Override
    public void apply(BaseCharacter character) {
        character.addManaRegen(character.getStat(Stat.INT)* BaseCharacter.MANA_REGEN_PER_INT);
    }

    @Override
    public boolean repeatable() {
        return true;
    }
}
