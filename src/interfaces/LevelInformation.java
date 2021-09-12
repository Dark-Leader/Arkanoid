// ID: 318528189.

package interfaces;
import geometry.Velocity;
import sprites.Block;
import java.util.List;

/**
 * <p>
 *     The LevelInformation application represents all the information a game level holds.
 * </p>
 */
public interface LevelInformation {
    /**
     * Return the number of balls.
     * @return int.
     */
    int numberOfBalls();

    /**
     * Return the list of velocities.
     * @return List<Velocity>.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Return the paddle speed.
     * @return int.
     */
    int paddleSpeed();

    /**
     * Return the paddle width.
     * @return int.
     */
    int paddleWidth();

    /**
     * Return the level name.
     * @return String.
     */
    String levelName();

    /**
     * Return the backGround of the level.
     * @return Sprite.
     */
    Sprite getBackground();

    /**
     * Return the list of blocks in the level.
     * @return List<Block>.
     */
    List<Block> blocks();

    /**
     * Return the number of blocks in the level.
     * @return int.
     */
    int numberOfBlocksToRemove();
}
