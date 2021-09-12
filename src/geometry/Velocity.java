// ID: 318528189.
package geometry;
import java.util.Random;

/**
 * <p>
 *     The Velocity application creates a Velocity object by taking 2 doubles dx and dy,
 *     or by taking a curtain speed and an angle and thus create a velocity object.
 *     It represents the velocity of an object (e.g : velocity of a moving car).
 *     In our case, how many pixels the object moves up, down, left, right on the GUI.
 * </p>
 */

public class Velocity {
    // x,y vectors for the Velocity object.
    private double xVector;
    private double yVector;

    /**
     * Constructor for the Velocity object.
     * @param dx Double.
     * @param dy Double.
     */
    public Velocity(double dx, double dy) {
        this.xVector = dx;
        this.yVector = dy;
    }

    /**
     * This function moves an object by changing its center point.
     * The new location of the object is: X = X + xVector, Y = Y + yVector.
     * In the end we return the new point.
     * @param p Point.
     * @return Point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + getXVector(), p.getY() + getYVector());
    }

    /**
     * This function creates a new Velocity object according to the angle and speed values provided.
     * First we convert angle to Radians.
     * Next we calculate the xVector using: dx = speed * sin(angleInRadians).
     * Next we calculate the yVector using: dy = -speed * cos(angleInRadians).
     * In the end we return the new velocity.
     * @param angle Double.
     * @param speed Double.
     * @return Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // calculating the X and Y vectors for the new Velocity object.
        double angleInRadians = Math.toRadians(angle);
        double dx = speed * Math.sin(angleInRadians);
        double dy = -speed * Math.cos(angleInRadians);
        // returning the new Velocity object.
        return new Velocity(dx, dy);
    }

    /**
     * This function returns the xVector of the Velocity object.
     * @return Double.
     */
    public double getXVector() {
        return this.xVector;
    }
    /**
     * This function returns the yVector of the Velocity object.
     * @return Double.
     */
    public double getYVector() {
        return this.yVector;
    }

    /**
     * This function creates a new Velocity object according to the value of the radius provided.
     * If the radius provided is greater than 50 we want to use 50, else use the value of radius.
     * Next we define the speed of our object to be 50 / radius.
     * Next we create an angle using the random generator.
     * Next we create a Velocity object using the angle and speed we just made.
     * In the end return that Velocity.
     * @param radius Int.
     * @param rand Random.
     * @return Velocity.
     */
    public static Velocity chooseVelocityBySize(int radius, Random rand) {
        // pointer to radius.
        double temp = (double) radius;
        double defaultSpeed = 50.0;
        // if the radius is greater than 50 we want to address it as 50.
        if (radius > 50) {
            temp = 50.0;
        }
        // speed of the object.
        double newSpeed = defaultSpeed / temp;
        // angle of the object.
        double angle = rand.nextDouble() * 360;
        // creating a Velocity object using speed and angle.
        Velocity velocity = Velocity.fromAngleAndSpeed(angle, newSpeed);
        // returning the Velocity object.
        return velocity;
    }
}
