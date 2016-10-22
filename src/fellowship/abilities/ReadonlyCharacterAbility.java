package fellowship.abilities;

public class ReadonlyCharacterAbility implements AbilityInterface {
    private final CharacterAbility ability;
    public ReadonlyCharacterAbility(CharacterAbility ability){
        this.ability = ability;
    }

    public int getNumSlots(){
        return ability.getNumSlots();
    }

    public boolean repeatable() {return ability.repeatable();}

    public String name(){
        return ability.getClass().getName();
    }

    public Class<? extends CharacterAbility> abilityClass(){
        return ability.getClass();
    }
}
