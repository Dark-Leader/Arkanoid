// ID: 318528189.

package levels;
import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;
import java.awt.Color;

/**
 * <p>
 *     The DirectHitBackground application is a Sprite that holds the background of the level.
 * </p>
 */
public class DirectHitBackground implements Sprite {
    /**
     * This function draws the background of the level.
     * @param d DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        int width = d.getWidth();
        int height = d.getHeight();
        d.setColor(Color.BLACK);
        int start = 0;
        // set the background color.
        d.fillRectangle(start, start, width, height);
        Point center = new Point(width / 2, height / 5);
        int radius = 75;
        int diff = 15;
        d.setColor(Color.BLUE);
        // draw the target.
        d.drawCircle((int) center.getX(), (int) center.getY(), radius);
        d.drawCircle((int) center.getX(), (int) center.getY(), radius - diff);
        d.drawCircle((int) center.getX(), (int) center.getY(), radius - diff * 2);
        d.drawLine(width / 2 - radius - diff, height / 5, width / 2 + radius + diff, height / 5);
        d.drawLine(width / 2, height / 5 - radius - diff, width / 2, height / 5 + radius + diff);
    }

    /**
     * This function is responsible for moving the sprite, since the background doesn't change we just return.
     */
    public void timePassed() {
        return;
    }
}
