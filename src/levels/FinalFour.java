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
 *     The Final Four application represents a level in the game.
 * </p>
 */
public class FinalFour implements LevelInformation {
    // variables for logic.
    private int numberOfBalls = 3;
    private List<Velocity> velocities = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    private int paddleVelocity = 10;
    private int paddleSize = 100;
    private Sprite backGround = new FinalFourBackground(); // change this!

    /**
     * Constructor, in addition we create the blocks and velocities of the balls.
     */
    public FinalFour() {
        // variables for the logic.
        int width = 800;
        int borderSize = 25;
        int height = 600;
        int blockHeight = 20;
        int blockWidth = 50;
        int xStart = 25;
        int yStart = height / 6;
        int numOfBlocksRow = (width - 2 * borderSize) / blockWidth;
        int numOfRows = 7;
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        /*
        Iterating over the number of blocks in each row and creating the blocks and adding them to the list of blocks.
         */
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfBlocksRow; j++) {
                // creating a block and adding it to the list of blocks.
                int x = xStart + j * blockWidth;
                int y = yStart + i * blockHeight;
                Point topLeft = new Point(x, y);
                Rectangle rec = new Rectangle(topLeft, blockWidth, blockHeight);
                Block block = new Block(rec, colors[i]);
                this.blocks.add(block);
            }
        }
        // creating the ball velocities.
        int speed = 7;
        int angle = 300;
        int angleChange = 10;
        /*
        making sure that all the velocities will have a negative y-Vector so that the balls will go up
        and give the player enough time to react and in addition don't make the balls too fast.
         */
        for (int i = 0; i < numberOfBalls; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(angle + i * angleChange, speed);
            this.velocities.add(velocity);
        }
    }

    /**
     * Return number of balls in the game.
     * @return int.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Return the paddle speed.
     * @return int.
     */
    public int paddleSpeed() {
        return this.paddleVelocity;
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

    /**
     * Return the level name.
     * @return String.
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * Return the background Sprite of the level.
     * @return Sprite.
     */
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * Return the list of blocks in the level.
     * @return List<Block>.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Return the number of blocks in the level.
     * @return int.
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
