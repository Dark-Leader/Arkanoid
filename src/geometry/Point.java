// ID: 318528189.
package geometry;
/**
 * <p>
 *     The Point application creates a Point object by taking 2 doubles - X-coordinate and Y-coordinate.
 *     It represents a point in 2D space.
 * </p>
 */

public class Point {
    // X and Y coordinates.
    private double x;
    private double y;

    /**
     * Constructor for a Point object, taking X and Y coordinates.
     * @param x Double.
     * @param y Double.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function returns the distance between 2 point using Pythagoras Theorem.
     * @param other Point.
     * @return double.
     */
    public double distance(Point other) {
        /*
        we create a right angled triangle by finding the distance on the X axis and distance on the Y axis.
        after that we calculate the sum of their squares and return the square root of that sum.
         */
        double yDiff = Math.pow(other.getY() - this.getY(), 2);
        double xDiff = Math.pow(other.getX() - this.getX(), 2);
        double totalDistance = Math.sqrt(xDiff + yDiff);
        return totalDistance;
    }

    /**
     * This function returns true if two point are the same by checking there X and Y coordinates, else false.
     * @param other Point.
     * @return boolean.
     */
    public boolean equals(Point other) {
        return (this.getY() == other.getY()) && (this.getX() == other.getX());
    }

    /**
     * This function returns the X axis coordinate of the Point it belongs to.
     * @return double.
     */
    public double getX() {
        return this.x;
    }

    /**
     * This function returns the Y axis coordinate of the Point it belongs to.
     * @return double.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Changing the x-Coordinate.
     * @param xPos double.
     */
    public void setX(double xPos) {
        this.x = xPos;
    }
    /**
     * Changing the Y-Coordinate.
     * @param yPos double.
     */
    public void setY(double yPos) {
        this.y = yPos;
    }
}