// ID: 318528189.
package interfaces;
import biuoop.DrawSurface;

/**
 * <p>
 *     The Animation application represents an Animation.
 * </p>
 */
public interface Animation {
    /**
     * This function draws one frame on the GUI, and update all the locations of the balls, paddle, blocks.
     * @param d DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This function tells the program whether or not the animation should stop.
     * @return boolean.
     */
    boolean shouldStop();
}
