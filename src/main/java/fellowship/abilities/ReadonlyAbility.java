package fellowship.abilities;

public final class ReadonlyAbility {
    private final Ability ability;
    public ReadonlyAbility(Ability ability){
        this.ability = ability;
    }

    public int getNumSlots(){
        return ability.getNumSlots();
    }

    public boolean repeatable() {return ability.repeatable();}

    public String name(){
        return ability.getName();
    }

    public Class<?> abilityClass(){
        if (ability instanceof ActionAbility) {
            ActionAbility actionAbility = (ActionAbility) ability;
            return actionAbility.actionClass();
        }
        return ability.getClass();
    }

    public int getRemaining(){
        return ability.getRemaining();
    }
}
