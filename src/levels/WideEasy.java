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
 *<p>
 *     The WideEasy application represents a level in the game.
 *</p>
 */
public class WideEasy implements LevelInformation {
    // variables for logic.
    private int numberOfBalls = 10;
    private List<Velocity> velocities = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    private int paddleVelocity = 10;
    private int paddleSize = 600;
    private Sprite backGround = new WideEasyBackground();

    /**
     * Constructor, in addition we create the ball velocities and blocks.
     */
    public WideEasy() {
        // variables for the logic.
       int height = 600;
       int xStart = 25;
       int yPosRow = height / 3;
       int blockWidth = 50;
       int numOfBlocks = 15;
       int blockHeight = 20;
       Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
       Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN};
       /*
       Create the blocks.
        */
       for (int i = 0; i < numOfBlocks; i++) {
           int x = xStart + i * blockWidth;
           Point topLeft = new Point(x, yPosRow);
           Rectangle rec = new Rectangle(topLeft, blockWidth, blockHeight);
           Block block = new Block(rec, colors[i]);
           this.blocks.add(block);
       }
       int baseAngle = 300;
       int angleChange = 10;
       int ballSpeed = 7;
       /*
       Create ball velocities.
        */
       for (int i = 0; i < numberOfBalls; i++) {
           Velocity velocity = Velocity.fromAngleAndSpeed(baseAngle + i * angleChange, ballSpeed);
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
        return "Wide Easy";
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
