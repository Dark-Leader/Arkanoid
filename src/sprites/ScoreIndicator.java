// ID:318528189.
package sprites;
import interfaces.Sprite;
import settings.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * <p>
 *     The ScoreIndicator application represents a counter that holds the score of the player in the game.
 * </p>
 */
public class ScoreIndicator implements Sprite {
    // variables for the logic.
    private Counter counter;
    private int xFirst;
    private int xSecond;
    private int y;
    private int fontSize = 15;
    private String levelName;

    /**
     * Contructor.
     * @param count Counter.
     * @param x1 int.
     * @param y int.
     * @param x2 int.
     * @param name String.
     */
    public ScoreIndicator(Counter count, int x1, int y, int x2, String name) {
        this.counter = count;
        this.xFirst = x1;
        this.y = y;
        this.xSecond = x2;
        this.levelName = name;
    }

    /**
     * Constructor.
     * @param count Counter.
     * @param x1 int.
     * @param y int.
     * @param x2 int.
     */
    public ScoreIndicator(Counter count, int x1, int y, int x2) {
        this.counter = count;
        this.xFirst = x1;
        this.y = y;
        this.xSecond = x2;
    }

    /**
     * Constructor.
     * @param x1 int.
     * @param y int.
     * @param x2 int.
     */
    public ScoreIndicator(int x1, int y, int x2) {
        this.xFirst = x1;
        this.y = y;
        this.xSecond = x2;
    }

    /**
     * Drawing the score on the GUI using the DrawSurface provided.
     * @param d DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.drawText(this.xFirst, this.y, "Score: " + this.counter.getValue(), this.fontSize);
        d.drawText(this.xSecond, this.y, this.levelName, this.fontSize);
    }

    /**
     * the ScoreIndicator might not change from iterations of the game so we don't do anything.
     * We need this function beacuse the ScoreIndicator implemets the Sprite interface.
     */
    public void timePassed() {
        return;
    }

    /**
     * This function changes the name of the level.
     * @param name String.
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * This function sets a new counter.
     * @param newCounter Counter.
     */
    public void setCounter(Counter newCounter) {
        this.counter = newCounter;
    }

    /**
     * This function returns the counter.
     * @return Counter.
     */
    public Counter getCounter() {
        return this.counter;
    }
}
