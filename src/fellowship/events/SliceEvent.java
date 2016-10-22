package fellowship.events;

import fellowship.Character;
import fellowship.events.Event;

public class SliceEvent extends Event {

    private double amount;
    private final Character slicer, sliced;
    public SliceEvent(Character slicer, Character sliced, double amount){
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

    public Character getSlicer(){
        return slicer;
    }

    public Character getSliced(){
        return sliced;
    }
}
