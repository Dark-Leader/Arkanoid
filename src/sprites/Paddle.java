// ID: 318528189.
package sprites;
import interfaces.Sprite;
import interfaces.Collidable;
import geometry.Rectangle;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import settings.GameLevel;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * <p>
 *     the Paddle application creates an object that implements Sprite and Collidable and is an object that its location
 *     is controlled by the user by pressing left arrow key to move left or pressing right arrow key to move right.
 * </p>
 */

public class Paddle implements Sprite, Collidable {

    // variables for the logic and operation of the paddle.
    private biuoop.KeyboardSensor keyboard;
    private double width;
    private double height;
    private Rectangle rectangle;
    private Line top;
    private Point topLeft;
    private Point topRight;
    private Point bottomRight;
    private Point bottomLeft;
    private Color color;
    private int speed = 10;
    private int screenWidthStart;
    private int screenWidthEnd;

    /**
     * Constructor for a paddle object.
     * @param rec Rectangle.
     * @param color java.awt.Color.
     * @param screenWidthStart int.
     * @param screenWidthEnd int.
     * @param keyboard KeyboardSensor.
     */
    public Paddle(Rectangle rec, Color color, int screenWidthStart, int screenWidthEnd, KeyboardSensor keyboard) {
        this.rectangle = rec;
        this.color = color;
        this.top = new Line(rec.getUpperLeft(), rec.getUpperRight());
        this.topLeft = this.top.start();
        this.topRight = this.top.end();
        this.bottomRight = new Point(this.topRight.getX(), this.topRight.getY() + height);
        this.bottomLeft = new Point(this.topLeft.getX(), this.topLeft.getY() + height);
        this.width = this.rectangle.getWidth();
        this.height = this.rectangle.getHeight();
        this.screenWidthStart = screenWidthStart;
        this.screenWidthEnd = screenWidthEnd;
        this.keyboard = keyboard;

    }

    /**
     * This function moves the paddle left (if it can move left).
     */
    public void moveLeft() {
        // if we won't pass the left border after moving.
        if (this.topLeft.getX() - this.speed > this.screenWidthStart) {
            // we update all the attributes of the paddle to its new location.
            Point newTopLeft = new Point(this.topLeft.getX() - this.speed, this.topLeft.getY());
            Rectangle newRec = new Rectangle(newTopLeft, this.width, this.height);
            setRectangle(newRec);
            this.topLeft = newRec.getUpperLeft();
            this.topRight = newRec.getUpperRight();
            this.bottomRight = newRec.getBottomRight();
            this.bottomLeft = newRec.getUpperLeft();
            this.top = newRec.getTop();
        } else {
            // we couldn't move left anymore so our position is at the border - and again we update all attributes.
            Point newTopLeft = new Point(screenWidthStart + 1, this.topLeft.getY());
            Rectangle newRec = new Rectangle(newTopLeft, this.width, this.height);
            setRectangle(newRec);
            this.topLeft = newRec.getUpperLeft();
            this.topRight = newRec.getUpperRight();
            this.bottomRight = newRec.getBottomRight();
            this.bottomLeft = newRec.getUpperLeft();
            this.top = newRec.getTop();
        }
    }

    /**
     * This function moves the paddle right (if it can move right).
     */
    public void moveRight() {
        // if we won't pass the right border after moving.
        if (this.topRight.getX() + this.speed < this.screenWidthEnd) {
            // we update all the attributes of the paddle to its new location.
            Point newTopLeft = new Point(this.topLeft.getX() + this.speed, this.topLeft.getY());
            Rectangle newRec = new Rectangle(newTopLeft, this.width, this.height);
            setRectangle(newRec);
            this.topLeft = newRec.getUpperLeft();
            this.topRight = newRec.getUpperRight();
            this.bottomRight = newRec.getBottomRight();
            this.bottomLeft = newRec.getUpperLeft();
            this.top = newRec.getTop();
        } else {
            // we couldn't move right anymore so our position is at the border, and again we update all attributes.
            Point newTopLeft = new Point(this.screenWidthEnd - this.width, this.topLeft.getY());
            Rectangle newRec = new Rectangle(newTopLeft, this.width, this.height);
            setRectangle(newRec);
            this.topLeft = newRec.getUpperLeft();
            this.topRight = newRec.getUpperRight();
            this.bottomRight = newRec.getBottomRight();
            this.bottomLeft = newRec.getUpperLeft();
            this.top = newRec.getTop();
        }
    }

    /**
     * This function is responsible for moving the paddle according to the user input.
     * if the user pressed the left arrow key then we call the moveLeft() function.
     * else if the user pressed the right arrow key then we call the moveRight() function.
     */
    public void timePassed() {
        // user pressed left arrow key.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            // attempting to move the paddle left.
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            // user pressed right arrow key.
            // attempting to move the paddle left.
            moveRight();
        }
    }

    /**
     * Drawing the paddle on the GUI with the DrawSurface provided.
     * @param d DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        // getting the dimensions of the paddle and its location.
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int paddleWidth = (int) this.width;
        int paddleHeight = (int) this.height;
        d.setColor(this.color);
        // drawing the paddle on the GUI.
        d.fillRectangle(x, y, paddleWidth, paddleHeight);
    }

    /**
     * Returning the paddle location. the paddle is basically a rectangle that the user can move.
     * @return Rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * This function changes the velocity of the according to where it hit the paddle.
     * We devided the top line of the paddle to 5 parts:
     * if the ball hit the left most part (part 1) then it gets a left Velocity.
     * else if the ball hit part 2 then it gets a slightly left Velocity.
     * else if the ball hit part 3 then we just change its Y-vector.
     * else if the ball hit part 4 then it gets a slightly right Velocity.
     * else if the ball hit part 5 then it gets a right Velocity.
     * else the Velocity doesn't change (I didn't change it since in the game if the ball goes below a curtain height
     * then the player lost or loses a life so the ball will despawn and thus collision that are not on the top
     * line of the paddle are not possible in the game).
     * @param collisionPoint Point.
     * @param currentVelocity Velocity.
     * @param hitter Ball.
     * @return Velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // if the collision is not on the top line of the paddle.
        if (!Line.pointOnSegment(this.top.start(), collisionPoint, this.top.end())) {
            return currentVelocity;
        }
        // here we split the top line into 5 lines.
        double partsWidth = this.width / 5;
        double x = this.top.start().getX();
        double y = this.top.start().getY();
        Point firstEnd = new Point(x + partsWidth, y);
        Line firstPart = new Line(this.topLeft, firstEnd);
        Point secondStart = new Point(firstPart.end().getX() + 1, y);
        Point secondEnd = new Point(secondStart.getX() + partsWidth, y);
        Line secondPart = new Line(secondStart, secondEnd);
        Point thirdStart = new Point(secondPart.end().getX() + 1, y);
        Point thirdEnd = new Point(thirdStart.getX() + partsWidth, y);
        Line thirdPart = new Line(thirdStart, thirdEnd);
        Point fourthStart = new Point(thirdPart.end().getX() + 1, y);
        Point fourthEnd = new Point(fourthStart.getX() + partsWidth, y);
        Line fourthPart = new Line(fourthStart, fourthEnd);
        Point fifthStart = new Point(fourthPart.end().getX() + 1, y);
        Point fifthEnd = this.topRight;
        Line fifthPart = new Line(fifthStart, fifthEnd);
        // if the collision is on the first part.
        if (Line.pointOnSegment(firstPart.start(), collisionPoint, firstPart.end())) {
            double angle = 300;
            double dx = currentVelocity.getXVector();
            double dy = currentVelocity.getYVector();
            double ballSpeed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            // getting a new velocity for the ball.
            Velocity newVelocity = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            return newVelocity;
        } else if (Line.pointOnSegment(secondPart.start(), collisionPoint, secondPart.end())) {
            // if the collision is on the second part.
            double angle = 330;
            double dx = currentVelocity.getXVector();
            double dy = currentVelocity.getYVector();
            double ballSpeed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            // getting a new velocity for the ball.
            Velocity newVelocity = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            return newVelocity;
        } else if (Line.pointOnSegment(thirdPart.start(), collisionPoint, thirdPart.end())) {
            // if the collision is on the third (middle) part then we just change the Y-Vector.
            return new Velocity(currentVelocity.getXVector(), -currentVelocity.getYVector());
        } else if (Line.pointOnSegment(fourthPart.start(), collisionPoint, fourthPart.end())) {
            // if the collision is on the fourth part.
            double angle = 30;
            double dx = currentVelocity.getXVector();
            double dy = currentVelocity.getYVector();
            double ballSpeed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            // getting new velocity for the ball.
            Velocity newVelocity = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            return newVelocity;
        } else if (Line.pointOnSegment(fifthPart.start(), collisionPoint, fifthPart.end())) {
            // if the collision is on the fifth part.
            double angle = 60;
            double dx = currentVelocity.getXVector();
            double dy = currentVelocity.getYVector();
            double ballSpeed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            // getting new velocity for the ball.
            Velocity newVelocity = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            return newVelocity;
        } else {
            return currentVelocity;
        }

    }

    /**
     * Adding the paddle to game.
     * @param level GameLevel.
     */
    public void addToGame(GameLevel level) {
        // adding the paddle as a sprite and as a collidable.
        level.addSprite(this);
        level.addCollidable(this);
    }

    /**
     * changing the location of the paddle.
     * @param rec Rectangle.
     */
    public void setRectangle(Rectangle rec) {
        this.rectangle = rec;
    }

    /**
     * Return speed of the paddle.
     * @return int.
     */
    public int getSpeed() {
        return this.speed;
    }
}
