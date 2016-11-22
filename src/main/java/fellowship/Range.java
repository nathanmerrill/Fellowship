package fellowship;

public class Range {
    private final boolean isCardinal;
    private final int distance;
    public Range(int range, boolean isCardinal){
        this.distance = range;
        this.isCardinal = isCardinal;
    }
    public Range(int range){
        this(range, false);
    }

    public Range asCardinal(){
        return new Range(this.distance, true);
    }

    public Range notCardinal(){
        return new Range(this.distance, false);
    }

    public Range shorter(int amount){
        return new Range(this.distance-amount, isCardinal);
    }

    public Range longer(int amount){
        return new Range(this.distance+amount, isCardinal);
    }

    public boolean isCardinal() {
        return isCardinal;
    }

    public int getDistance() {
        return distance;
    }

    public int getRange() {
        return Math.max(distance, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Range range1 = (Range) o;

        if (isCardinal != range1.isCardinal) return false;
        return distance == range1.distance;

    }

    @Override
    public int hashCode() {
        int result = (isCardinal ? 1 : 0);
        result = 31 * result + distance;
        return result;
    }
}
