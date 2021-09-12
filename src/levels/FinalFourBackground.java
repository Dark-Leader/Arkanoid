// ID: 318528189.

package levels;
import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * <p>
 *     The FinalFourBackground application creates a Sprite that holds the backGround of the Final Four level.
 * </p>
 */
public class FinalFourBackground implements Sprite {
    /**
     * This function creates the background and draws it on the DrawSurface provided.
     * @param d DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        int width = d.getWidth();
        int height = d.getHeight();
        int start = 0;
        // painting the background in blue.
        Color blue = new Color(54, 117, 156);
        d.setColor(blue);
        d.fillRectangle(start, start, width, height);
        // creating the colors of the clouds.
        Color lightGray = new Color(204, 204, 204);
        Color gray = new Color(187, 187, 187);
        Color darkGray = new Color(170, 170, 170);

        // creating the left clouds group.
        // variables for the logic.
        int leftCircleLeftCloudX = width / 6;
        int leftCircleLeftCloudY = (height / 3) * 2;
        int radius = 20;
        int secondRadius = 30;
        int xLeft = leftCircleLeftCloudX - radius;
        int change = radius / 2;
        int lines = 10;
        d.setColor(Color.WHITE);
        // drawing the lines for rain of the left group.
        for (int i = 0; i < lines; i++) {
            d.drawLine(leftCircleLeftCloudX + change * i, leftCircleLeftCloudY, xLeft + i * change, height);
        }
        d.setColor(lightGray);
        // drawing the left clouds group.
        d.fillCircle(leftCircleLeftCloudX, leftCircleLeftCloudY, radius);
        d.fillCircle(leftCircleLeftCloudX + radius, leftCircleLeftCloudY + radius, radius);
        d.setColor(gray);
        d.fillCircle(leftCircleLeftCloudX + radius * 2, leftCircleLeftCloudY - radius / 2, secondRadius);
        d.setColor(darkGray);
        d.fillCircle(leftCircleLeftCloudX + 2 * radius + secondRadius,
                leftCircleLeftCloudY - radius / 4, secondRadius);
        d.fillCircle(leftCircleLeftCloudX + secondRadius + radius * 4 / 3,
                leftCircleLeftCloudY + radius * 2 / 3, radius);
        // variables for the right clouds group.
        int leftCircleRightCloudX = width * 5 / 6;
        int leftCircleRightCloudY = height * 5 / 6;
        int xRight = leftCircleRightCloudX - radius;

        d.setColor(Color.WHITE);
        // drawing the lines of rain for the right group.
        for (int i = 0; i < lines; i++) {
            d.drawLine(leftCircleRightCloudX + change * i, leftCircleRightCloudY, xRight + i * change, height);
        }
        d.setColor(lightGray);
        // drawing the right clouds group.
        d.fillCircle(leftCircleRightCloudX, leftCircleRightCloudY, radius);
        d.fillCircle(leftCircleRightCloudX + radius * 4 / 5, leftCircleRightCloudY + radius * 3 / 2, radius * 4 / 3);
        d.setColor(gray);
        d.fillCircle(leftCircleRightCloudX + radius * 2, leftCircleRightCloudY - radius / 3, secondRadius);
        d.setColor(darkGray);
        d.fillCircle(leftCircleRightCloudX + 2 * radius + secondRadius,
                leftCircleRightCloudY - radius / 4, secondRadius);
        d.fillCircle(leftCircleRightCloudX + secondRadius + radius * 4 / 5 + radius / 4,
                leftCircleRightCloudY + radius / 3, radius);
    }

    /**
     * The background doesn't change so we just return.
     */
    public void timePassed() {
        return;
    }
}
