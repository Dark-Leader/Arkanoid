// ID: 318528189.
package settings;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;

import java.util.ArrayList;
/**
 * <p>
 *     The GameEnvironment application stores all the collidables objects inside our game.
 * </p>
 */

public class GameEnvironment {
    // array to store all the collidable objects.
    private ArrayList<Collidable> collideables = new ArrayList<Collidable>();

    /**
     * Adding a collidable to our game.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        this.collideables.add(c);
    }

    /**
     * This function calculates the closest collision point on the trajectory line.
     * Basically we need to find which collsion happened first to update the velocity of the ball accordingly.
     * @param trajectory Line.
     * @return CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // variables for the logic.
        Point closestCollisionPoint = null;
        double distance;
        double minimum = Double.POSITIVE_INFINITY;
        int size = collideables.size();
        int index = -1;
        /*
         * iterating over all the collidables we store.
         * if a collision will happen between the ball trajectory and the collidable object then we check what
         * distance is the collision point from the start of the trajectory line and create a CollisionInfo object
         * that stores the collision point and the collidable object the ball hit and return this CollisionInfo object.
         * else if no collisions happened than we return null.
         */
        ArrayList<Collidable> updatedCollideables = new ArrayList<Collidable>(this.collideables);
        for (int i = 0; i < size; i++) {
            Rectangle rectangle = updatedCollideables.get(i).getCollisionRectangle();
            // checking if a collision will happen.
            Point intersection = trajectory.closestIntersectionToStartOfLine(rectangle);
            // if no collision happened.
            if (intersection == null) {
                continue;
            }
            // we find the distance from the start of the trajectory line to the collision point.
            distance = intersection.distance(trajectory.start());
            // we compare the distance we found to minimum one and if it is smaller..
            if (distance < minimum) {
                // we update the index, minimum and closestCollisionPoint.
                index = i;
                minimum = distance;
                closestCollisionPoint = intersection;
            }
        }
        // if no collisions happened.
        if (closestCollisionPoint == null) {
            return null;
        }
        // else a collsion happened so we return a new CollisionInfo object with the information we found.
        return new CollisionInfo(collideables.get(index), closestCollisionPoint);

    }

    /**
     * Returning the list of collidable objects in the game.
     * @return ArrayList.
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collideables;
    }

    /**
     * Remove Collidable from the array of Collidables.
     * @param c Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collideables.remove(c);
    }

}
