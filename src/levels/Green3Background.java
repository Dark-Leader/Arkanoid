// ID: 318528189.
package levels;
import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * <p>
 *     The Green3Background application creates a Sprite that holds the background of the Green3 level.
 * </p>
 */
public class Green3Background implements Sprite {
    /**
     * Draws the background Sprite.
     * @param d DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        int start = 0;
        int width = d.getWidth();
        int height = d.getHeight();
        int darkGreen = 102;
        Color green = new Color(start, darkGreen, start);
        d.setColor(green);
        // fill the background with dark green background.
        d.fillRectangle(start, start, width, height);
        // variables for the logic.
        int smallRecHeight = 20;
        int smallRecWidth = 10;
        int space = 5;
        int xStart = height / 8;
        int numberOfSmallRec = 5;
        int spacesOnWidth = 6;
        int yStart = height - (smallRecHeight * numberOfSmallRec + space * numberOfSmallRec);
        int bigRecWidth = smallRecWidth * numberOfSmallRec + space * spacesOnWidth;
        int bigRecHeight = height - yStart;
        int grayRGB = 38;
        int whiteRGB = 250;
        Color darkGray = new Color(grayRGB, grayRGB, grayRGB);
        d.setColor(darkGray);
        // now we start drawing the tower at the bottom left.
        d.fillRectangle(xStart, yStart, bigRecWidth, bigRecHeight);
        Color white = new Color(whiteRGB, whiteRGB, whiteRGB);
        d.setColor(white);
        /*
        Draws the windows in the building.
         */
        for (int i = 1; i < numberOfSmallRec + 1; i++) {
            for (int j = 1; j < numberOfSmallRec + 1; j++) {
                int x = xStart + j * space + smallRecWidth * j - smallRecWidth;
                int y = yStart + i * space + smallRecHeight * i - smallRecHeight;
                // draw a window.
                d.fillRectangle(x, y, smallRecWidth, smallRecHeight);
            }
        }
        // drawing the base on top of the building.
        int middleRecWidth = smallRecWidth * 3;
        int middleRecHeight = smallRecHeight * 3;
        grayRGB = 55;
        Color gray = new Color(grayRGB, grayRGB, grayRGB);
        xStart = xStart + space * 3 + smallRecWidth;
        yStart = yStart - middleRecHeight;
        d.setColor(gray);
        d.fillRectangle(xStart, yStart, middleRecWidth, middleRecHeight);
        // drawing the long pole at the top of the building.
        int longPoleHeight = middleRecHeight * 3;
        xStart = xStart + middleRecWidth / 3;
        yStart = yStart - longPoleHeight;
        grayRGB = 70;
        Color darkerGray = new Color(grayRGB, grayRGB, grayRGB);
        d.setColor(darkerGray);
        d.fillRectangle(xStart, yStart, smallRecWidth, longPoleHeight);
        // draw the beacon at the top of the tower.
        int xCenter = xStart + smallRecWidth / 2;
        int yCenter = yStart - smallRecHeight / 4;
        int r = 234;
        int g = 209;
        int b = 209;
        int radius = 7;
        Color lightGray = new Color(r, g, b);
        d.setColor(lightGray);
        d.fillCircle(xCenter, yCenter, radius);
        radius -= 2;
        r = 255;
        g = 0;
        b = 0;
        Color red = new Color(r, g, b);
        d.setColor(red);
        d.fillCircle(xCenter, yCenter, radius);
        radius -= 3;
        d.setColor(white);
        d.fillCircle(xCenter, yCenter, radius);
    }

    /**
     * This Sprite doesn't move so we just return.
     */
    public void timePassed() {
        return;
    }
}
