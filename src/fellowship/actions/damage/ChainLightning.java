package fellowship.actions.damage;

import fellowship.characters.BaseCharacter;
import fellowship.actions.Action;

public class ChainLightning extends Action {

    public ChainLightning(BaseCharacter character){
        super(character);
    }

    @Override
    public void perform() {
        character.enemyCharacters()
                .forEach(enemy -> enemy.damage(character, 15));
    }

    @Override
    public int getCooldown() {
        return 7;
    }

    @Override
    public int getManaCost() {
        return 20;
    }
}
