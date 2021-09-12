// ID:318528189.
package interfaces;
import sprites.Block;
import sprites.Ball;

/**
 * <p>
 *     The HitListener application is an interface for listening for collisions between balls and blocks.
 * </p>
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * beingHit is the block being hit by a ball.
     * hitter is the hitting ball.
     * @param beingHit Block.
     * @param hitter Ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
