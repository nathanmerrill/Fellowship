package fellowship.events;

import fellowship.characters.BaseCharacter;

public class SliceEvent extends Event {

    private double amount;
    private final BaseCharacter slicer, sliced;
    public SliceEvent(BaseCharacter slicer, BaseCharacter sliced, double amount){
        this.slicer = slicer;
        this.sliced = sliced;
        this.amount = amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return amount;
    }

    public BaseCharacter getSlicer(){
        return slicer;
    }

    public BaseCharacter getSliced(){
        return sliced;
    }
}
