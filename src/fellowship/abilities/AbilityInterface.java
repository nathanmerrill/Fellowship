package fellowship.abilities;

public interface AbilityInterface {

    int getNumSlots();
    boolean repeatable();
    String name();
    Class<? extends AbilityInterface> abilityClass();
}
