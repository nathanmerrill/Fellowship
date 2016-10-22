package fellowship.actions;

public interface ActionInterface {
    int getManaCost();
    int getCooldown();
    boolean isAvailable();
    boolean basicAction();
    boolean breaksInvisibility();
    boolean movementAction();
    Class<? extends CharacterAction> actionClass();
}
