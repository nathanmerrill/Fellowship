package fellowship;

public class Range {
    private boolean isCardinal;
    private int range;
    public Range(int range, boolean isCardinal){

    }
    public Range(int range){
        this(range, false);
    }

    public boolean isCardinal() {
        return isCardinal;
    }

    public int getRange() {
        return range;
    }
}
