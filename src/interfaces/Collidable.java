// ID: 318528189.
package interfaces;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * <p>
 *     The Collidable application is an interface for objects that the ball can collide with.
 * </p>
 */

public interface Collidable {
    /**
     * Returning the rectangle the ball collided with.
     * @return Rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * This function is responsible for creating a new velocity for the ball after the collision happened.
     * @param collisionPoint Point.
     * @param currentVelocity Velocity.
     * @param hitter Ball.
     * @return Velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
