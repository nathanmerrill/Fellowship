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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Range range1 = (Range) o;

        if (isCardinal != range1.isCardinal) return false;
        return range == range1.range;

    }

    @Override
    public int hashCode() {
        int result = (isCardinal ? 1 : 0);
        result = 31 * result + range;
        return result;
    }
}
