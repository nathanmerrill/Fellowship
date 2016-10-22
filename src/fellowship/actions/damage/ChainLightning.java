package fellowship.actions.damage;

import fellowship.Character;
import fellowship.actions.CharacterAction;

public class ChainLightning extends CharacterAction{

    public ChainLightning(Character character){
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
