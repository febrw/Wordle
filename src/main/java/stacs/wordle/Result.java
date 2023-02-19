package stacs.wordle;

/** Result stores a mapping of guess results to their ASCII colour counterparts.
 *  @author 210025499
 */ 
public enum Result {
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    RED("\u001B[31m"),
    NONE("\u001B[37m");
    public final String val;

    private Result(String val) {
        this.val = val;
    }
    /** Used to automatically convert a Result to its enum value during string printing. */
    @Override
    public String toString() {
        return val;
    }
}
