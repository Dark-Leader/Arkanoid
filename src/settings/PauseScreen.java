// ID: 318528189.

package settings;
import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * <p>
 *   The PauseScreen application represents a pause screen for the player if he pressed a curtain button.
 * </p>
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * This function draws the pause screen, currently we just show a message and pause the game.
     * @param d DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(100, d.getHeight() / 2, "paused -- press space to continue", 32);
        this.stop = true;
    }

    /**
     * Return whether or not the pause screen should end.
     * @return boolean.
     */
    public boolean shouldStop() {
        return stop;
    }

}
