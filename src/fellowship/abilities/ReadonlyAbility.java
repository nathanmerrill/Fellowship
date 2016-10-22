package fellowship.abilities;

public class ReadonlyAbility implements AbilityInterface {
    private final Ability ability;
    public ReadonlyAbility(Ability ability){
        this.ability = ability;
    }

    public int getNumSlots(){
        return ability.getNumSlots();
    }

    public boolean repeatable() {return ability.repeatable();}

    public String name(){
        return ability.getClass().getName();
    }

    public Class<? extends Ability> abilityClass(){
        return ability.getClass();
    }
}
