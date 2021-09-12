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
 *     This function represents a level in the game.
 * </p>
 */
public class DirectHit implements LevelInformation {
    // variables for the logic.
    private int numberOfBalls = 1;
    private List<Velocity> velocities = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    private int paddleVelocity = 10;
    private int paddleSize = 100;
    private Sprite backGround = new DirectHitBackground();

    /**
     * Constructor, this function creates all the ball velocities and the blocks.
     */
    public DirectHit() {
        int width = 800;
        int height = 600;
        int blockHeight = 20;
        Point topLeftPointBlock = new Point(width / 2 - blockHeight / 2, height / 5 - blockHeight / 2);
        Rectangle rec = new Rectangle(topLeftPointBlock, blockHeight, blockHeight);
        Block block = new Block(rec, Color.RED);
        // add the block to the list.
        this.blocks.add(block);
        Velocity velocity = new Velocity(0, -8);
        // add the velocity to the list.
        this.velocities.add(velocity);
    }

    /**
     * Return number of balls in the game.
     * @return int.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Return the speed of the paddle.
     * @return int.
     */
    public int paddleSpeed() {
        return this.paddleVelocity;
    }

    /**
     * Return the list of velocities of the balls.
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
        return "Direct Hit";
    }

    /**
     * Return the background of the level.
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
