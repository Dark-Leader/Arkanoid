// ID: 318528189.
package settings;
import java.util.ArrayList;
import biuoop.DrawSurface;
import interfaces.Sprite;

/**
 * <p>
 *     The SpriteCollection application stores all the Sprite objects in the game.
 * </p>
 */

public class SpriteCollection {
    // storing all the sprites in an array.
    private ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * Adding a sprite to the game.
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * moving all the sprites in the game (if they can move).
     */
    public void notifyAllTimePassed() {
        /*
         * iterating over the sprites and moving them in space (if they can move).
         */
        ArrayList<Sprite> updatedSprites = new ArrayList<>(this.sprites);
        for (Sprite sp : updatedSprites) {
            // attempting to move the sprite.
            sp.timePassed();
        }
    }

    /**
     * Returning the sprites in the game.
     * @return ArrayList.
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Drawing all the sprites in the game on the GUI with the DrawSurface provided.
     * @param d DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        /*
         * iterating over the sprites and drawing them.
         */
        for (Sprite sp : sprites) {
            // drawing the sprite.
            sp.drawOn(d);
        }
    }

    /**
     * Remove sprite from the array.
     * @param sp Sprite.
     */
    public void removeSprite(Sprite sp) {
        this.sprites.remove(sp);
    }
}
