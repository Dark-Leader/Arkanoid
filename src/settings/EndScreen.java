// ID: 318528189.

package settings;
import biuoop.DrawSurface;
import interfaces.Animation;
import java.awt.Color;

/**
 * <p>
 *     The EndScreen application creates an end screen after the game is finished that tells the player if he won
 *     or not and displays their score.
 * </p>
 */
public class EndScreen implements Animation {
    private int x;
    private int y;
    private String message;
    private boolean stop = false;
    private int score;

    /**
     * Constructor.
     * @param message String.
     * @param x int.
     * @param y int.
     * @param score int.
     */
    public EndScreen(String message, int x, int y, int score) {
        this.message = message;
        this.x = x;
        this.y = y;
        this.score = score;
    }

    /**
     * Should the animation continue?
     * @return boolean.
     */
    public boolean shouldStop() {
        return stop;
    }

    /**
     * This function displays the result of the game - if the player won or lost and their score.
     * @param d DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        int start = 0;
        int fontSize = 32;
        d.setColor(Color.CYAN);
        d.fillRectangle(start, start, d.getWidth(), d.getHeight());
        d.setColor(Color.ORANGE);
        d.drawText(x, y, message + " Your score is " + score, fontSize);
        this.stop = true;
    }
}
