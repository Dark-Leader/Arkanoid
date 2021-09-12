// ID: 318528189.
package interfaces;
import biuoop.DrawSurface;

/**
 * <p>
 *     The sprite application is an interface for all the objects in our game.
 *     we need to know how to draw the object and move it (if it can move).
 * </p>
 */

public interface Sprite {
    /**
     * We need to know how to draw the sprite on the DrawSurface provided.
     * @param d DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * We need to know how to move the sprite (if it can move).
     */
    void timePassed();
}
