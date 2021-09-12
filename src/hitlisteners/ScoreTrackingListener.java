// ID: 318528189.
package hitlisteners;
import interfaces.HitListener;
import settings.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * <p>
 *     The ScoreTrackingListener application represents a counter for specific tasks -
 *     adding points when a block is hit by a ball.
 * </p>
 */
public class ScoreTrackingListener implements HitListener {
    // counter.
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Increasing the counter because a ball hit a block.
     * @param beingHit Block.
     * @param hitter Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }

    /**
     * Return the current score.
     * @return Counter.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * Setting a new Counter.
     * @param newScore Counter.
     */
    public void setCurrentScore(Counter newScore) {
        this.currentScore = newScore;
    }

    /**
     * If a full row of blocks were deleted we add 100 points to the counter.
     */
    public void deletedRow() {
        currentScore.increase(100);
    }
}
