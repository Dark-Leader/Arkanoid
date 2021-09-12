// ID: 318528189.
package geometry;
import java.util.ArrayList;
/**
 * <p>
 *     The Rectangle application represents a rectangle in 2D space.
 *     it has 4 points and a color.
 * </p>
 */

public class Rectangle {
    // variables for the logic.
    private double width;
    private double height;
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;
    private Line leftSide;
    private Line rightSide;
    private Line top;
    private Line bottom;

    /**
     * Constructor for a Rectangle.
     * @param upperLeft Point.
     * @param width double.
     * @param height double.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.topLeft = upperLeft;
        this.topRight = new Point(this.topLeft.getX() + width, this.topLeft.getY());
        this.bottomLeft = new Point(this.topLeft.getX(), this.topLeft.getY() + height);
        this.bottomRight = new Point(this.topRight.getX(), this.bottomLeft.getY());
        this.leftSide = new Line(this.bottomLeft, this.topLeft);
        this.rightSide = new Line(this.bottomRight, this.topRight);
        this.top = new Line(this.topLeft, this.topRight);
        this.bottom = new Line(this.bottomLeft, this.bottomRight);
    }

    /**
     * This function find all the intersection points between a rectangle and a line and stores them in an array
     * and returns said array.
     * @param line Line.
     * @return java.util.List.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // setting up the array and getting the 4 lines in the rectangle.
        ArrayList<Point> intersectionPoint = new ArrayList<>();
        Line[] lines = new Line[4];
        // storing the lines of the rectangle in an array.
        lines[0] = new Line(this.topLeft, this.topRight);
        lines[1] = new Line(this.bottomLeft, this.topLeft);
        lines[2] = new Line(this.bottomRight, this.topRight);
        lines[3] = new Line(this.bottomLeft, this.bottomRight);
        /*
         * iterating over the array we made, checking if there is an intersection point between the line provided
         * and the line we are currently checking, if so we add the intersection point to the ArrayList.
         */
        for (Line l1: lines) {
            Point intersect = l1.intersectionWith(line);
            // if there is an intersection point
            if (intersect != null) {
                // adding the point to the array.
                intersectionPoint.add(intersect);
            }
        }
        // returning the ArrayList..
        return intersectionPoint;

    }

    /**
     * Getting the width of the rectangle.
     * @return double.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Getting the height of the rectangle.
     * @return double.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Getting the upper left point.
     * @return Point.
     */
    public Point getUpperLeft() {
        return this.topLeft;
    }
    /**
     * Getting the bottom left point.
     * @return Point.
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }
    /**
     * Getting the upper right point.
     * @return Point.
     */
    public Point getUpperRight() {
        return this.topRight;
    }
    /**
     * Getting the bottom right point.
     * @return Point.
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }
    /**
     * Getting the upper line.
     * @return Line.
     */
    public Line getTop() {
        return this.top;
    }
    /**
     * Getting the right line.
     * @return Line.
     */
    public Line getRightSide() {
        return this.rightSide;
    }
    /**
     * Getting the bottom line.
     * @return Line.
     */
    public Line getBottom() {
        return this.bottom;
    }
    /**
     * Getting the left line.
     * @return Line.
     */
    public Line getLeftSide() {
        return this.leftSide;
    }
}


