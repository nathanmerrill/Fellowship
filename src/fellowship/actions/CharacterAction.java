package fellowship.actions;

import fellowship.Character;
import fellowship.events.Events;

public abstract class CharacterAction {
    protected final Character character;
    public int cooldown;
    public CharacterAction(Character character){
        this.character = character;
        cooldown = 0;
    }
    public final void act(){
        if (!isAvailable()){
            return;
        }
        cooldown = getCooldown()*(5-character.cleverness())/5;
        int manaCost = Math.max(0, getManaCost()-(2*character.smartness()));
        this.character.removeMana(manaCost);
        perform();
        if (cooldown > 0) {
            character.on(Events.TurnStart, event ->  (--cooldown) == 0);
        }
    }

    public abstract void perform();
    public abstract int getManaCost();
    public abstract int getCooldown();
    public boolean isAvailable(){
        return cooldown == 0
                && this.character.getMana() >= getManaCost();
    }
    public boolean basicAction(){
        return false;
    }
    public boolean breaksInvisibility(){
        return true;
    }
    public boolean movementAction(){
        return false;
    }
}
