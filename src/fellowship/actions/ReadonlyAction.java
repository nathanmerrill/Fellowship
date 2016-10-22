package fellowship.actions;


public class ReadonlyAction implements ActionInterface {

    private final Action action;
    public ReadonlyAction(Action action){
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
    public final Class<? extends Action> actionClass(){
        return action.getClass();
    }
}
