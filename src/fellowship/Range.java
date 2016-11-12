package fellowship;

public class Range {
    private final boolean isCardinal;
    private final int range;
    public Range(int range, boolean isCardinal){
        this.range = range;
        this.isCardinal = isCardinal;
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
