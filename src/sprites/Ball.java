// ID: 318528189.
package sprites;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import settings.CollisionInfo;
import settings.GameLevel;
import settings.GameEnvironment;

/**
 * <p>
 *     The Ball application creates a Ball object by taking 1 point and a radius and a color,
 *     or taking 1 point,radius,color,velocity.
 *     This application represents a Ball which is moving in space.
 * </p>
 */

public class Ball implements Sprite {
    // center point for the ball,radius,color,velocity. setting the default velocity to 0 (ball is not moving).
    private GameEnvironment game;
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);

    /**
     * Constructor for a Ball object that has no velocity at the moment (velocity is set to 0 in default).
     * Taking 2 Doubles to create the center point of the ball.
     * Taking an Int for the radius of the ball (its size).
     * And taking a color for the ball's color.
     * @param x Double.
     * @param y Double.
     * @param r Int.
     * @param color java.awt.Color.
     * @param gameEnv GameEnvironment.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnv) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.game = gameEnv;
    }

    /**
     * Constructor for a Ball object that has a velocity, and a center point given.
     * Taking one point to be the center of the ball.
     * Taking an Int for the radius of the ball.
     * Taking a Velocity to be the velocity of the ball.
     * @param center Point.
     * @param r int.
     * @param color java.awt.Color.
     * @param gameEnv GameEnvironment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnv) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.game = gameEnv;
    }

    /**
     * This function returns the X-coordinate of the center point of the ball.
     * @return int.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * This function returns the Y-coordinate of the center point of the ball.
     * @return int.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * This function returns the radius of the ball (its size).
     * @return Int.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * This function returns the color of the ball.
     * @return java.awt.Color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This function draws the ball object on the DrawSurface object provided by drawing a circle on the surface
     * provided with the same center point, same radius, and the color of the ball.
     * @param surface DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * This function changes the velocity value of the ball object.
     * @param v Velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This function changes the velocity value of the ball object.
     * We take two double arguments and convert them to a Velocity object and set the velocity value of the ball
     * to the Velocity object we created.
     * @param dx Double.
     * @param dy Double.
     */
    public void setVelocity(double dx, double dy) {
        // creating the new velocity and setting it.
        Velocity v = new Velocity(dx, dy);
        this.velocity = v;
    }

    /**
     * returns the velocity of the ball.
     * @return Velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This function is responsible for moving the ball in space and updating its location and if it hit a collidable
     * object then we change the velocity of the ball according to the collision location and the current velocity
     * of the ball.
     */
    public void moveOneStep() {
        // variables for the logic.
        GameEnvironment gameEnv = this.game;
        Point ballCenter = this.center;
        double endX = Math.floor(center.getX() + this.getVelocity().getXVector());
        double endY = Math.floor(center.getY() + this.getVelocity().getYVector());
        Point endPoint = new Point(endX, endY);
        Line trajectory = new Line(center, endPoint);
        // checking if collisions will happen if the ball keeps its current velocity.
        CollisionInfo info = gameEnv.getClosestCollision(trajectory);
        // if no collisions will happen.
        if (info == null) {
            // we update the location of the ball.
            this.setCenter(endPoint);
            return;
        }
        // else there was a collision.
        Point collision = info.getCollisionPoint();
        Collidable object = info.getCollisionObject();
        Velocity current = this.getVelocity();
        // getting new velocity according to the location of the collision and the type of the collidable object.
        Velocity newVelocity = object.hit(this, collision, current);
        // some variables to calculate the new location of the ball after the collision.
        Rectangle rectangle = object.getCollisionRectangle();
        Line leftSide = rectangle.getLeftSide();
        Line top = rectangle.getTop();
        Line rightSide = rectangle.getRightSide();
        Line bottom = rectangle.getBottom();
        /*
         * now we will check the collision point and according to it and the current velocity of the ball we will
         * update its location (center point) and in the end we will update its velocity.
         */
        // if the ball hit the bottom left corner of the rectangle.
        if (collision.equals(leftSide.start())) {
            if (current.getXVector() > 0 && current.getYVector() > 0) {
                // if the ball is moving right and down.
                double x = collision.getX() - 1;
                double y = collision.getY() - 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            } else if (current.getXVector() > 0 && current.getYVector() < 0) {
                // if the ball is moving right and up.
                double x = collision.getX() - 1;
                double y = collision.getY() + 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            } else if (current.getXVector() < 0 && current.getYVector() < 0) {
                // if the ball is moving left and up.
                double x = collision.getX() + 1;
                double y = collision.getY() + 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            }
        } else if (collision.equals(leftSide.end())) {
            // if the ball hit the top left corner of the rectangle.
            if (current.getXVector() > 0 && current.getYVector() > 0) {
                // if the ball is moving right and down.
                double x = collision.getX() - 1;
                double y = collision.getY() - 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            } else if (current.getXVector() > 0 && current.getYVector() < 0) {
                // if the ball is moving right and up.
                double x = collision.getX() - 1;
                double y = collision.getY() + 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            } else if (current.getXVector() < 0 && current.getYVector() > 0) {
                // if the ball is moving left and down.
                double x = collision.getX() + 1;
                double y = collision.getY() - 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            }
        } else if (collision.equals(rightSide.start())) {
            // if the ball hit the bottom right corner of the rectangle.
            if (current.getXVector() > 0 && current.getYVector() < 0) {
                // if the ball is moving right and up.
                double x = collision.getX() - 1;
                double y = collision.getY() + 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            } else if (current.getXVector() < 0 && current.getYVector() < 0) {
                // if the ball is moving left and up.
                double x = collision.getX() + 1;
                double y = collision.getY() + 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            } else if (current.getXVector() < 0 && current.getYVector() > 0) {
                // if the ball is moving left and down.
                double x = collision.getX() + 1;
                double y = collision.getY() - 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            }
        } else if (collision.equals(rightSide.end())) {
            // if the ball hit the top right corner of the rectangle.
            if (current.getXVector() > 0 && current.getYVector() > 0) {
                // if the ball is moving right and down.
                double x = collision.getX() - 1;
                double y = collision.getY() - 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            } else if (current.getXVector() < 0 && current.getYVector() > 0) {
                // if the ball is moving left and down.
                double x = collision.getX() + 1;
                double y = collision.getY() - 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            } else if (current.getXVector() < 0 && current.getYVector() < 0) {
                // if the ball is moving left and up.
                double x = collision.getX() + 1;
                double y = collision.getY() + 1;
                Point newCenter = new Point(x, y);
                this.setCenter(newCenter);
            }
        } else if (Line.pointOnSegment(leftSide.start(), collision, leftSide.end())) {
            // if the ball hit the left line of the rectangle.
            double x = collision.getX() - 1;
            double y = collision.getY();
            Point newCenter = new Point(x, y);
            this.setCenter(newCenter);
        } else if (Line.pointOnSegment(top.start(), collision, top.end())) {
            // if the ball hit the top line of the rectangle.
            double x = collision.getX();
            double y = collision.getY() - 1;
            Point newCenter = new Point(x, y);
            this.setCenter(newCenter);
        } else if (Line.pointOnSegment(rightSide.start(), collision, rightSide.end())) {
            // if the ball hit the right line of the rectangle.
            double x = collision.getX() + 1;
            double y = collision.getY();
            Point newCenter = new Point(x, y);
            this.setCenter(newCenter);
        } else {
            // if the ball hit the bottom line of the rectangle.
            double x = collision.getX();
            double y = collision.getY() + 1;
            Point newCenter = new Point(x, y);
            this.setCenter(newCenter);
        }
        // update the velocity of the ball.
        this.setVelocity(newVelocity);
    }

    /**
     * returning the game environment.
     * @return GameEnvironment.
     */
    public GameEnvironment getGame() {
        return game;
    }

    /**
     * Setting a new game environment.
     * @param gameEnv GameEnvironment.
     */
    public void setGame(GameEnvironment gameEnv) {
        this.game = gameEnv;
    }

    /**
     * Setting a new Center Point.
     * @param newCenter Point.
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * This function is responsible of calling the function that moves the ball.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * This function is responsible for adding the ball to the game, meaning adding it the sprites collection.
     * @param level GameLevel.
     */
    public void addToGame(GameLevel level) {
        level.addSprite(this);
    }

}


