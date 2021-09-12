// ID: 318528189.
package hitlisteners;

import interfaces.HitListener;
import settings.Counter;
import settings.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * <P>
 *     The BallRemover application removes a ball from the game.
 * </P>
 */
public class BallRemover implements HitListener {
    // variables for logic.
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param level GameLevel.
     * @param remainingBalls Counter.
     */
    public BallRemover(GameLevel level, Counter remainingBalls) {
        this.gameLevel = level;
        this.remainingBalls = remainingBalls;
    }

    // Balls that are outside of the screen should be removed from the game.

    /**
     * This function removes the ball from the game
     * and decreases the counter that holds the number of balls in the game.
     * @param beingHit Block.
     * @param hitter Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeSprite(hitter);
        this.remainingBalls.decrease(1);
    }
}
