package fellowship.events;

import fellowship.characters.BaseCharacter;

public class DamageEvent extends Event {

    private final BaseCharacter damager, damaged;
    private double amount;
    public DamageEvent(BaseCharacter damager, BaseCharacter damaged, double amount){
        this.damaged = damaged;
        this.damager = damager;
        this.amount = amount;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public BaseCharacter getDamager(){
        return damager;
    }

    public BaseCharacter getDamaged() {
        return damaged;
    }

    @Override
    public void cancel() {

    }
}
