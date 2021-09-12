// ID: 318528189.
package settings;

/**
 * <p>
 *     The Counter application represents a counter.
 * </p>
 */
public class Counter {
    // variable for logic.
    private int currentCount;

    /**
     * Constructor.
     * @param value int.
     */
    public Counter(int value) {
        this.currentCount = value;
    }

    /**
     * Add a number to the counter.
     * @param number int.
     */
    public void increase(int number) {
        this.currentCount += number;
    }
    /**
     * Subtract a number to the counter.
     * @param number int.
     */
    public void decrease(int number) {
        this.currentCount -= number;
    }

    /**
     * Getting the current count value.
     * @return int.
     */
    public int getValue() {
        return this.currentCount;
    }

    /**
     * Setting a new count value.
     * @param value int.
     */
    public void setCurrentCount(int value) {
        this.currentCount = value;
    }
}