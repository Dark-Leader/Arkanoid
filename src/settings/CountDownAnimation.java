// ID: 318528189.
package settings;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Animation;
import java.awt.Color;

/**
 * <p>
 *     The CountDownAnimation represents a count down from a number to a number before the game starts.
 * </p>
 */
public class CountDownAnimation implements Animation {
    // variables for logic.
    private boolean stop = false;
    private Sleeper sleeper = new Sleeper();
    private boolean first = false;
    private boolean second = false;
    private boolean third = false;
    private SpriteCollection sprites;
    private double numOfSeconds;
    private int countFrom;
    private int time;

    /**
     * Constructor.
     * @param sprites SpriteCollection.
     * @param numOfSeconds double.
     * @param countFrom int.
     */
    public CountDownAnimation(SpriteCollection sprites, double numOfSeconds, int countFrom) {
        this.sprites = sprites;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.time = (int) (numOfSeconds / countFrom) * 1000;
    }

    /**
     * This function draws one frame on the GUI.
     * @param d DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        if (this.numOfSeconds == 0) { // if the animation should stop.
            this.stop = true;
        }
        int border = 25;
        int x = d.getWidth() / 2;
        int y = d.getHeight() / 2 + 2 * border;
        int number = this.countFrom;
        // draw all the sprites.
        this.sprites.drawAllOn(d);
        d.setColor(Color.RED);
        // draw the count down.
        d.drawText(x, y, String.valueOf(number), 32);
        this.countFrom--;
        this.numOfSeconds--;
        sleeper.sleepFor(time);
    }

    /**
     * Return whether or not the animtaion should stop.
     * @return boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
