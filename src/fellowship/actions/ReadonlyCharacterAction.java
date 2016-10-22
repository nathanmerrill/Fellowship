package fellowship.actions;


public class ReadonlyCharacterAction implements ActionInterface {

    private final CharacterAction action;
    public ReadonlyCharacterAction(CharacterAction action){
        this.action = action;
    }

    public int getManaCost(){
        return action.getManaCost();
    }
    public int getCooldown(){
        return action.getCooldown();
    }
    public boolean isAvailable(){
        return action.isAvailable();
    }
    public boolean basicAction(){
        return action.basicAction();
    }
    public boolean breaksInvisibility(){
        return action.breaksInvisibility();
    }
    public boolean movementAction(){
        return action.movementAction();
    }
    public final Class<? extends CharacterAction> actionClass(){
        return action.getClass();
    }
}
