// ID: 318528189.
package sprites;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import settings.GameLevel;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * <p>
 *     The Block application represents a barrier that if the ball hits it, the balls velocity needs to change.
 *     The block is a rectangle that implements Collidable and Sprite.
 * </p>
 */

public class Block implements Collidable, Sprite, HitNotifier {
    // some variables for the logic.
    private Rectangle rec;
    private Line leftSide;
    private Line rightSide;
    private Line top;
    private Line bottom;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * Constructor for a Block object.
     * @param rectangle Rectangle.
     * @param color java.awt.Color.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rec = rectangle;
        this.bottom = new Line(rectangle.getBottomLeft(), rectangle.getBottomRight());
        this.top = new Line(rectangle.getUpperLeft(), rectangle.getUpperRight());
        this.leftSide = new Line(rectangle.getBottomLeft(), rectangle.getUpperLeft());
        this.rightSide = new Line(rectangle.getBottomRight(), rectangle.getUpperRight());
        this.color = color;
    }

    /**
     * Returning the collision object, meaning the rectangle that the block is located at.
     * @return Rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * This function is responsible for deciding what new velocity should the ball that hit the Block should receive.
     * First we check at what point did the collision happen, and then according to that point we create a new
     * Velocity vector and return it.
     * @param collisionPoint Point.
     * @param currentVelocity Velocity.
     * @param hitter Ball.
     * @return Velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // some variables for the logic.
        Rectangle rectangle = this.rec;
        Line leftLine = rectangle.getLeftSide();
        Line rightLine = rectangle.getRightSide();
        Line topLine = rectangle.getTop();
        Line bottomLine = rectangle.getBottom();
        // bottom left corner.
        try {
            if (collisionPoint.equals(leftLine.start())) {
                if (currentVelocity.getXVector() > 0 && currentVelocity.getYVector() > 0) {
                    // if the ball is moving right and down.
                    double xVector = -currentVelocity.getXVector();
                    double yVector = currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                } else if (currentVelocity.getXVector() > 0 && currentVelocity.getYVector() < 0) {
                    // if the ball is moving right and up.
                    double xVector = currentVelocity.getXVector();
                    double yVector = -currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                } else if (currentVelocity.getXVector() < 0 && currentVelocity.getYVector() < 0) {
                    // if the ball is moving left and up.
                    double xVector = currentVelocity.getXVector();
                    double yVector = -currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                }
            } else if (collisionPoint.equals(leftLine.end())) { // top left corner.
                if (currentVelocity.getXVector() > 0 && currentVelocity.getYVector() > 0) {
                    // if the ball is moving right and down.
                    double xVector = currentVelocity.getXVector();
                    double yVector = -currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                } else if (currentVelocity.getXVector() > 0 && currentVelocity.getYVector() < 0) {
                    // if the ball is moving right and up.
                    double xVector = -currentVelocity.getXVector();
                    double yVector = currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                } else if (currentVelocity.getXVector() < 0 && currentVelocity.getYVector() > 0) {
                    // if the ball is moving left and down.
                    double xVector = currentVelocity.getXVector();
                    double yVector = -currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                }
            } else if (collisionPoint.equals(rightLine.start())) { // bottom right corner.
                if (currentVelocity.getXVector() < 0 && currentVelocity.getYVector() < 0) {
                    // if the ball is moving left and down.
                    double xVector = currentVelocity.getXVector();
                    double yVector = -currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                } else if (currentVelocity.getXVector() < 0 && currentVelocity.getYVector() > 0) {
                    // if the ball is moving left and up.
                    double xVector = -currentVelocity.getXVector();
                    double yVector = currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                } else if (currentVelocity.getXVector() > 0 && currentVelocity.getYVector() < 0) {
                    // if the ball is moving right and down.
                    double xVector = currentVelocity.getXVector();
                    double yVector = -currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                }
            } else if (collisionPoint.equals(rightLine.end())) { // top right corner.
                if (currentVelocity.getXVector() > 0 && currentVelocity.getYVector() > 0) {
                    // if the ball is moving right and down.
                    double xVector = currentVelocity.getXVector();
                    double yVector = -currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                } else if (currentVelocity.getXVector() < 0 && currentVelocity.getYVector() > 0) {
                    // if the ball is moving left and up.
                    double xVector = currentVelocity.getXVector();
                    double yVector = -currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                } else if (currentVelocity.getXVector() < 0 && currentVelocity.getYVector() < 0) {
                    // if the ball is moving left and up.
                    double xVector = -currentVelocity.getXVector();
                    double yVector = currentVelocity.getYVector();
                    return new Velocity(xVector, yVector);
                }
            } else if (Line.pointOnSegment(leftLine.start(), collisionPoint, leftLine.end())
                    && currentVelocity.getXVector() > 0) {
                // if the collision is on the left face.
                double xVector = -currentVelocity.getXVector();
                double yVector = currentVelocity.getYVector();
                return new Velocity(xVector, yVector);
            } else if (Line.pointOnSegment(topLine.start(), collisionPoint, topLine.end())
                    && currentVelocity.getYVector() > 0) {
                // if the collision is on the top face.
                double xVector = currentVelocity.getXVector();
                double yVector = -currentVelocity.getYVector();
                return new Velocity(xVector, yVector);
            } else if (Line.pointOnSegment(rightLine.start(), collisionPoint, rightLine.end())
                    && currentVelocity.getXVector() < 0) {
                // if the collision is on the right face.
                double xVector = -currentVelocity.getXVector();
                double yVector = currentVelocity.getYVector();
                return new Velocity(xVector, yVector);
            } else if (Line.pointOnSegment(bottomLine.start(), collisionPoint, bottomLine.end())
                    && currentVelocity.getYVector() < 0) {
                // if the collision is on the bottom face.
                double xVector = currentVelocity.getXVector();
                double yVector = -currentVelocity.getYVector();
                return new Velocity(xVector, yVector);
            }
            return new Velocity(currentVelocity.getXVector(), currentVelocity.getYVector());
        } finally {
            this.notifyHit(hitter);
        }

    }

    /**
     * Returninig the left face line.
     * @return Line.
     */
    public Line getLeftSide() {
        return this.leftSide;
    }

    /**
     * Returning the bottom face line.
     * @return Line.
     */
    public Line getBottom() {
        return this.bottom;
    }

    /**
     * Returning the right face line.
     * @return Line.
     */
    public Line getRightSide() {
        return this.rightSide;
    }

    /**
     * Returning the top face line.
     * @return Line.
     */
    public Line getTop() {
        return this.top;
    }

    /**
     * Returning the rectangle that the block is located at.
     * @return Rectangle.
     */
    public Rectangle getRec() {
        return rec;
    }

    /**
     * This function is responsible for drawing the Block on the DrawSurface object provided.
     * @param surface DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        // getting the measurement of the Block - basically the rectangle.
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        int width = (int) this.rec.getWidth();
        int height = (int) this.rec.getHeight();
        // drawing it on the DrawSurfaceObject.
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * The way the Block moves.. it doesn't so the function doesn't do anything.
     * We need it for the Sprite interface.
     */
    public void timePassed() {
        return;
    }

    /**
     * Adding the Block to the game.
     * @param level GameLevel.
     */
    public void addToGame(GameLevel level) {
        // adding the Block to the game as a Sprite and as a Collidable.
        level.addSprite(this);
        level.addCollidable(this);
    }

    /**
     * Removes the block from the game.
     * @param level GameLevel.
     */
    public void removeFromGame(GameLevel level) {
        level.removeCollidable(this);
        level.removeSprite(this);
    }

    /**
     * Adding a listener to the Block to detect collisions.
     * @param hl HitListener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * Remove a listener from the Block.
     * @param hl HitListener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all the listeners that a collision with a ball happened.
     * @param hitter Ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
