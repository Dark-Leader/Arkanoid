// ID: 318528189.

package levels;
import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * <p>
 *     The WideEasyBackground represents a background for the WideEasy level.
 * </p>
 */
public class WideEasyBackground implements Sprite {
    /**
     * This function is responsible for drawing the background.
     * @param d DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        int start = 0;
        Color blue = new Color(55, 125, 160);
        d.setColor(blue);
        // first we fill the background with blue color.
        d.fillRectangle(start, start, d.getWidth(), d.getHeight());
        // next we create the sun at the top left corner.
        int xPos = d.getWidth() / 5;
        int yPos = d.getHeight() / 5;
        int radius = 60;
        Color orange = new Color(205, 176, 93);
        d.setColor(orange);
        d.fillCircle(xPos, yPos, radius);
        radius -= 7;
        Color strongOrange = new Color(120, 101, 50);
        d.setColor(strongOrange);
        d.fillCircle(xPos, yPos, radius);
        radius -= 7;
        Color yellow = Color.YELLOW;
        d.setColor(yellow);
        d.fillCircle(xPos, yPos, radius);
        // now we draw the lines from the sun to the blocks in the level.
        int xStart = 20;
        int yRowPos = d.getHeight() / 3;
        int lineChange = 10;
        int numOfLines = 70;
        d.setColor(strongOrange);
        /*
        draw lines from the sun to the blocks.
         */
        for (int i = 0; i < numOfLines; i++) {
            int x = xStart + i * lineChange;
            d.drawLine(xPos, yPos, x, yRowPos);
        }
    }

    /**
     * The background doesn't change so we just return.
     */
    public void timePassed() {
        return;
    }
}
