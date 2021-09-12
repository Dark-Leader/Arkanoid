// ID : 318528189.
package settings;

import geometry.Point;
import interfaces.Collidable;

/**
 * <p>
 *     The CollisionInfo application creates an object that stores all the information about the collision that
 *     occurred between a ball and a block / paddle in our game.
 *     It holds the collision point and the object that the ball hit.
 * </p>
 */


public class CollisionInfo {
    // object the ball hit and the collision point.
    private Collidable collisionObject;
    private Point collisionPoint;

    /**
     * Constructor for a CollisionInfo object.
     * @param object Collidable.
     * @param collision Point.
     */
    public CollisionInfo(Collidable object, Point collision) {
        this.collisionObject = object;
        this.collisionPoint = collision;
    }

    /**
     * Returning the Point the collision happened.
     * @return Point.
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returning the Collidable object the ball hit.
     * @return Collidable.
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
}
