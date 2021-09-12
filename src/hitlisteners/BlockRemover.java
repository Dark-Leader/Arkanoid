// ID: 318528189.
package hitlisteners;

import interfaces.HitListener;
import settings.Counter;
import settings.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * <p>
 *     The BlockRemover application removes a block from the game.
 * </p>
 */
public class BlockRemover implements HitListener {
    // variables for the logic.
    private GameLevel level;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param level GameLevel.
     * @param removedBlocks Counter.
     */
    public BlockRemover(GameLevel level, Counter removedBlocks) {
        this.level = level;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Removes blocks from the game because they were hit by a ball.
     * @param beingHit Block.
     * @param hitter Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // decrease number of blocks remaining in the game.
        this.remainingBlocks.decrease(1);
        // remove the block from the game.
        this.level.removeCollidable(beingHit);
        this.level.removeSprite(beingHit);
        beingHit.removeFromGame(this.level);
        beingHit.removeHitListener(this);
    }

    /**
     * Setting a new game.
     * @param newLevel GameLevel.
     */
    public void setGame(GameLevel newLevel) {
        this.level = newLevel;
    }

    /**
     * Set a new Counter.
     * @param newCounter Counter.
     */
    public void setRemainingBlocks(Counter newCounter) {
        this.remainingBlocks = newCounter;
    }

    /**
     * Get the counter.
     * @return Counter.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Get the game.
     * @return Game.
     */
    public GameLevel getGame() {
        return level;
    }
}
