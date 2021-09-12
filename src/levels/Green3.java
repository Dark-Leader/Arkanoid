// ID: 318528189.

package levels;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     The Green3 application holds all the information about the green3 level.
 * </p>
 */
public class Green3 implements LevelInformation {
    // variables for the logic.
    private int numberOfBalls = 2;
    private List<Velocity> velocities = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    private int paddleVelocity = 10;
    private int paddleSize = 100;
    private Sprite backGround = new Green3Background();

    /**
     * Constructor, in addition we create all the ball velocities and blocks.
     */
    public Green3() {
        int width = 800;
        int height = 600;
        int borderSize = 25;
        int blockHeight = 20;
        int xStart = height / 2 - borderSize;
        int yStart = height / 3;
        int numOfRows = 5;
        int numOfBlocksFirstRow = 10;
        int blockWidth = 50;
        Color[] colors = {Color.DARK_GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        /*
        Create all the blocks and add them to the list.
         */
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j + i < numOfBlocksFirstRow; j++) {
                Point topLeftPoint = new Point(xStart + j * blockWidth + i * blockWidth, yStart + i * blockHeight);
                Rectangle rec = new Rectangle(topLeftPoint, blockWidth, blockHeight);
                Block block = new Block(rec, colors[i]);
                blocks.add(block);
            }
        }
        int ballSpeed = 7;
        // add the velocities to the list.
        Velocity first = Velocity.fromAngleAndSpeed(300, ballSpeed);
        Velocity second = Velocity.fromAngleAndSpeed(330, ballSpeed);
        velocities.add(first);
        velocities.add(second);
    }

    /**
     * Return number of bals.
     * @return int.
     */
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /**
     * Return paddle width.
     * @return int.
     */
    public int paddleSpeed() {
        return paddleVelocity;
    }

    /**
     * Return the level name.
     * @return String.
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * Return the list of blocks of the level.
     * @return List<Block>.
     */
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * Return the number of blocks in the level.
     * @return int.
     */
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }

    /**
     * Return the background Sprite.
     * @return Sprite.
     */
    public Sprite getBackground() {
        return backGround;
    }

    /**
     * Return the list of ball velocities.
     * @return List<Velocity>.
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * Return the paddle width.
     * @return int.
     */
    public int paddleWidth() {
        return this.paddleSize;
    }
}
