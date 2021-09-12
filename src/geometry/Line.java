// ID: 318528189.
package geometry;
import java.util.List;

/**
 * <p>
 *     The Line application creates a Line object by taking 2 Points objects,
 *     or by taking 2 x-coordinates and 2 y-coordinates.
 *     It represents a line in 2D space.
 * </p>
 */

public class Line {
    // start and end points for the line.
    private Point start;
    private Point end;

    /**
     * Constructor for a Line object, taking 2 points.
     * @param start Point.
     * @param end Point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor for a Line object, taking 4 coordinates - 2 X-coordinates and 2 Y-coordinates.
     * @param x1 Double.
     * @param y1 Double.
     * @param x2 Double.
     * @param y2 Double.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(x2, y2);
        this.start = startPoint;
        this.end = endPoint;
    }

    /**
     * This function returns the middle point of a Line object by calculating the half point of the sum
     * of the X and Y coordinates.
     * @return Point.
     */
    public Point middle() {
        /*
        getting the sum of each value in its axis and creating a Point object that is the middle of Line and
        returning the point.
         */
        double xMid = (this.start().getX() + this.end().getX()) / 2;
        double yMid = (this.start().getY() + this.end().getY()) / 2;
        Point mid = new Point(xMid, yMid);
        return mid;
    }

    /**
     * This function returns the start Point object of a Line object.
     * @return Point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * This function returns the end Point object of a Line object.
     * @return Point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * This function checks if two Lines (segments) intersect each other at some point.
     * First we check the orientation of all the points that construct the two lines,
     * if the first and second orientations are different and the third and fourth orientations are different then
     * the lines intersect at some point.
     * next we check the special cases for intersection - if the two lines are collinear then they might have many
     * intersection points and thus we need to return false but if they only have 1 intersection point -
     * meaning that the end or start point of the lines is the start or end point of the other line then there is
     * only one intersection point (because they are collinear).
     * if none of those conditions are met, then the lines do not intersect and thus we return false.
     * if any of the conditions above are met, then the lines intersect at some point ONCE and thus we return true.
     * @param other Line.
     * @return boolean.
     */
    public boolean isIntersecting(Line other) {
        /*
        getting the orientations of all the points that construct the 2 lines.
         */
        int firstOrientation = orientationOfPoints(this.start, this.end, other.start);
        int secondOrientation = orientationOfPoints(this.start, this.end, other.end);
        int thirdOrientation = orientationOfPoints(other.start, other.end, this.start);
        int fourthOrientation = orientationOfPoints(other.start, other.end, this.end);

        // if the pairs of orientation are different then the lines intersect and thus we return true.
        if ((firstOrientation != secondOrientation) && (thirdOrientation != fourthOrientation)) {
            return true;
        }
        // now we check the special cases - if the lines are collinear.

        /*
        if firstOrientation == 0 then the lines are collinear so we check if other.end is on the segment this.start to
        this.end, if so we need to make sure that the lines have only one single intersection point by checking that
        other.start equals this.start or other.start equals this.end.
        if all the conditions are met we return true because the lines have only one intersection point even though
        they are collinear and other.end lay on the segment this.start to this.end.
         */
        if ((firstOrientation == 0) && (pointOnSegment(this.start, other.start, this.end))
                && (other.start.equals(this.start) || other.start().equals(this.end))) {
            return true;
        }
        /*
        if secondOrientation == 0 then the lines are collinear so we check if other.end is on the segment this.start to
        this.end, if so we need to make sure that the lines have only one single intersection point by checking that
        other.end equals this.start or other.end equals this.end.
        if all the conditions are met we return true because the lines have only one intersection point even though
        they are collinear and other.end lay on the segment this.start to this.end.
         */
        if ((secondOrientation == 0) && (pointOnSegment(this.start, other.end, this.end))
                && (other.end.equals(this.start) || other.end.equals(this.end))) {
            return true;
        }
        /*
        if thirdOrientation == 0 then the lines are collinear so we check if this.start is on the segment other.start
        to other.end, if so we need to make sure that the lines have only one single intersection point by checking
        that this.start equals other.start or this.start equals other.end.
        if all the conditions are met we return true because the lines have only one intersection point even though
        they are collinear and this.start lay on the segment other.start to other.end.
         */
        if ((thirdOrientation == 0) && (pointOnSegment(other.start, this.start, other.end))
                && (this.start.equals(other.start) || this.start.equals(other.end) || this.start.equals(this.end())
                || other.start().equals(other.end()))) {
            return true;
        }
        /*
         * if fourthOrientation == 0 then the lines are collinear so we check if this.end is on the segment other.
         * start to other.end, if so we need to make sure that the lines have only one single intersection point by
         * checking that this.end equals other.start or this.end equals other.end.
         * if all the conditions are met we return true because the lines have only one intersection point even though
         * they are collinear and this.end lay on the segment other.start to other.end.
         */
        if ((fourthOrientation == 0) && (pointOnSegment(other.start, this.end, other.end))
                && (this.end.equals(other.start) || this.end.equals(other.end))) {
            return true;
        }

        // if only the conditions were not met then the lines don't intersect so we return false.
        return false;
    }

    /**
     * This function returns the intersection point between two lines (if it exists) else returns null.
     * First we check if the two lines intersect, if not we return null.
     * else : each line can be represented by Y = aX + b,
     * Y is the y-coordinate, a is the slope of the line, X is the x-coordinate and b is the Y-interception coordinate
     * on the Y axis.
     * thus, in order to find the intersection point between the lines, we say that the Y value is the same so we
     * compare: aX + b = cX + d. now, we need to isolate X.
     * we can do it like so: X = (d - c)/(a - b). and thus we found the X-coordinate for the intersection point, now
     * all that is left is to place that X value in one the line equations and return that point.
     * @param other Line
     * @return Point.
     */
    public Point intersectionWith(Line other) {
        // checking if the lines have a single intersection point
        if (isIntersecting(other)) { // if they do:
            // we initialize some variables for the logic of finding the intersection point.
            double xCoordinate = 0;
            double yCoordinate = 0;
            double firstSlope;
            double secondSlope;
            double secondYIntercept;
            double firstYIntercept;
            // edge case: if a line is just a single point.
            if (this.start().equals(this.end())) {
                xCoordinate = this.start.getX();
                yCoordinate = this.start.getY();
                return new Point(xCoordinate, yCoordinate);
            }
            // edge case: if a line is just a single point.
            if (other.start().equals(other.end())) {
                xCoordinate = other.start.getX();
                yCoordinate = other.start.getY();
                return new Point(xCoordinate, yCoordinate);
            }
            /*
             * getting the slopes of the lines according to the equation: slope = (y2 - y1)/(x2 - x1).
             */
            firstSlope = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            secondSlope = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
            /*
             * getting the Y-intercept point for the two lines according to the equation: y = a * x + b.
             * and thus: b = y - a * x.
             * plus making sure that the lines aren't collinear with y-axis.
             */
            if (Math.abs(firstSlope) != Double.POSITIVE_INFINITY) { // not collinear with y-axis.
                firstYIntercept = this.start.getY() - (firstSlope * this.start.getX());
            } else { // collinear with y-axis.
                xCoordinate = this.start.getX();
                secondYIntercept = other.start.getY() - (secondSlope * other.start.getX());
                yCoordinate = secondSlope * xCoordinate + secondYIntercept;
                return new Point(xCoordinate, yCoordinate);
            }
            if (Math.abs(secondSlope) != Double.POSITIVE_INFINITY) { // not collinear with x-axis.
                secondYIntercept = other.start.getY() - (secondSlope * other.start.getX());
            } else { // collinear with x-axis.
                xCoordinate = other.start.getX();
                yCoordinate = firstSlope * xCoordinate + firstYIntercept;
                return new Point(xCoordinate, yCoordinate);
            }
            /*
             * finding the x and y coordinates for the intersection point of the lines.
             * x = (d - c)/(a - b).
             * y = a * x + b
             * making sure that we don't divide by 0 if the slopes are equal.
             * if they are equal then the lines are collinear and thus the intersection point the start or end point
             * of one of the lines, we deal with this later.
             */
            if (firstSlope != secondSlope) {
                if (firstSlope != Double.POSITIVE_INFINITY) { // making sure to not divide by infinity.
                    xCoordinate = (secondYIntercept - firstYIntercept) / (firstSlope - secondSlope);
                    yCoordinate = (firstSlope * xCoordinate) + firstYIntercept;
                } else if (secondSlope != Double.POSITIVE_INFINITY) { // making sure to not divide by infinity.
                    xCoordinate = (secondYIntercept - firstYIntercept) / (firstSlope - secondSlope);
                    yCoordinate = (firstSlope * xCoordinate) + firstYIntercept;
                } else if (firstSlope == Double.POSITIVE_INFINITY) { // making sure to not divide by 0.
                    xCoordinate = this.start.getX();
                    yCoordinate = secondSlope * xCoordinate + secondYIntercept;
                } else if (secondSlope == Double.POSITIVE_INFINITY) { // making sure to not divide by 0.
                    xCoordinate = other.start.getX();
                    yCoordinate = firstSlope * xCoordinate + firstYIntercept;
                }
            } else {
                // same slopes, lines are collinear and thus the intersection is start / end point of one of the lines.
                // here we check all possible combinations to find where is the intersection point.
                if (this.start.equals(other.start())) {
                    xCoordinate = this.start.getX();
                    yCoordinate = this.start.getY();
                } else if (this.start.equals(other.end())) {
                    xCoordinate = this.start.getX();
                    yCoordinate = this.start.getY();
                } else if (this.end.equals(other.start())) {
                    xCoordinate = this.end.getX();
                    yCoordinate = this.end.getY();
                } else if (this.end.equals(this.end())) {
                    xCoordinate = this.end.getX();
                    yCoordinate = this.end.getY();
                }
            }
            // returning the intersection point we found.
            return new Point(xCoordinate, yCoordinate);
        }
        // if there is no intersection point we return null.
        return null;
    }

    /**
     * This function checks if a given line is equal to another line by comparing their start and end points.
     * return true if they are equal, else false.
     * @param other Line.
     * @return boolean.
     */
    public boolean equals(Line other) {
        // return true if they are equal, else false.
        return this.start.equals(other.start) && this.end.equals(other.end);
    }


    /**
     * This function checks if Point p2 lay on the segment p1-p3.
     * First we check that x and y coordinates of Point p2 are between the values of (Xp1 and Xp3) and between the
     * values of (Yp1 and Yp3) by checking that Xp2 >= min(Xp1,Xp3) and Xp2 <= max(Xp1,Xp3) and
     * Yp2 >= min(Yp1,Yp3) and Yp2 <= max(Yp1,Yp3).
     * if all there conditions are met then Point p2 lay on the segment p1-p3 so we return true, else false.
     * @param p1 Point.
     * @param p2 Point.
     * @param p3 Point.
     * @return boolean.
     */
    public static boolean pointOnSegment(Point p1, Point p2, Point p3) {
        double x = p2.getX();
        double y = p2.getY();
        double biggestXPositionPossible = Math.max(p1.getX(), p3.getX()); // upper bound for X.
        double smallestXPositionPossible = Math.min(p1.getX(), p3.getX()); // lower bound for X.
        double biggestYPositionPossible = Math.max(p1.getY(), p3.getY()); // upper bound for Y.
        double smallestYPositionPossible = Math.min(p1.getY(), p3.getY()); // lower bound for Y.
        /*
         * checking that the values of x,y are between the upper and lower bounds.
         */
        boolean validXPosition = (x <= biggestXPositionPossible) && (x >= smallestXPositionPossible);
        boolean validYPosition = (y <= biggestYPositionPossible) && (y >= smallestYPositionPossible);
        // returning true if they are, else false.
        return validXPosition && validYPosition;
    }

    /**
     * This function checks the orientation of 3 points.
     * Orientation can be defined by the slopes two lines: p1-p2 and p2-p3. where l1: Y = aX + b, l2: Y = cX + d.
     * slopes can be found by: a = (y2 - y1)/(x2 - x1), c = (y3 - y2)/(x3 - x2).
     * now, if a == c ,so, a - c = 0 then the lines are collinear. so we return 0.
     * if a > c then a - c > 0 then the orientation of the lines is clockwise. so we return 1.
     * else a < c then a - c < 0 then the orientation of the lines is counter-clockwise. so we return -1.
     * meaning that the final equation is: (y2 - y1)/(x2 - x1) - (y3 - y2)/(x3 - x2).
     * if we expand it so there are no denominators we get:
     * (x3 - x2)*(y2 - y1) - (x2 - x1)*(y3 - y2). and this is the expression we use below to calculate
     * the result variable value.
     * @param p1 Point.
     * @param p2 Point.
     * @param p3 Point.
     * @return int. 0 for collinear, 1 for clockwise, -1 for counter clockwise.
     */
    public int orientationOfPoints(Point p1, Point p2, Point p3) {
        // getting the orientation by calculating which slope is greater.
        double result = ((p2.getY() - p1.getY()) * (p3.getX() - p2.getX()))
                - ((p2.getX() - p1.getX()) * (p3.getY() - p2.getY()));

        // if result == 0 then the lines are collinear. so we return 0.
        if (result == 0) {
            return 0;
        } else if (result > 0) { // if result > 0 then the orientation is clockwise so we return 1.
            return 1;
        } else {
            return -1; // else the orientation is counter-clockwise so we return -1.
        }
    }

    /**
     * This function finds the closest intersection to the start of the line with the Rectangle provided.
     * First we find all the intersection points.
     * Next we calculate which point is the closest point and return it.
     * @param rect Rectangle
     * @return Point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
         // getting all intersection points from a helper function.
         List<Point> intersectionPoints = rect.intersectionPoints(this);
         // if no collisions happened.
         if (intersectionPoints.isEmpty()) {
             return null;
         }
         // variables for the logic of finding the closest intersection point.
         double minimum = Double.POSITIVE_INFINITY;
         int index = 0;
         double distance;
         /*
          * iterating over the points in the array and calculating the distance from the beginning of line,
          * if the distance if smaller than the minimum then we update the minimum and the index variable.
          * in the end we return the point at the index we found to be the closest collision point in the array.
          */
         for (int i = 0; i < intersectionPoints.size(); i++) {
             // calculating the distance.
             distance = intersectionPoints.get(i).distance(this.start);
             // if we found a closer point.
             if (distance < minimum) {
                 // update the minimum and the index.
                 minimum = distance;
                 index = i;
             }
         }
         // return the point we found.
         return intersectionPoints.get(index);

    }

    /**
     * Setting the end point of a line.
     * @param endPoint Point.
     */
    public void setEnd(Point endPoint) {
        this.end = endPoint;
    }

    /**
     * Setting the start point of a line.
     * @param startPoint Point.
     */
    public void setStart(Point startPoint) {
        this.start = startPoint;
    }
}