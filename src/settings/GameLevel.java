// ID: 318528189.
package settings;
import java.awt.Color;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.ScoreTrackingListener;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;

/**
 * <p>
 *     The game application initializes a new game and runs it.
 *     it holds all the objects in the game.
 * </p>
 */

public class GameLevel implements Animation {
    // variables for the logic.
    private Counter numOfBlocksRemaining = new Counter(0);
    private Counter numOfBallsRemaining = new Counter(0);
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private BlockRemover remover = new BlockRemover(this, new Counter(0));
    private ScoreTrackingListener score = new ScoreTrackingListener(new Counter(0));
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private boolean countDownOver = false;
    private LevelInformation information;
    private BallRemover ballRemover = new BallRemover(this, this.numOfBallsRemaining);

    /**
     * Constructor.
     * @param runner AnimationRunner.
     * @param information LevelInformation.
     * @param counter Counter.
     */
    public GameLevel(AnimationRunner runner, LevelInformation information, Counter counter) {
        this.runner = runner;
        this.running = true;
        this.keyboard = runner.getGui().getKeyboardSensor();
        this.information = information;
        this.numOfBallsRemaining.setCurrentCount(information.numberOfBalls());
        this.remover.getRemainingBlocks().setCurrentCount(information.numberOfBlocksToRemove());
        this.score.setCurrentScore(counter);
    }
    /**
     * Adding a Collidable object to the game.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adding a Sprite object to the game.
     * @param sp Sprite.
     */

    public void addSprite(Sprite sp) {
        this.sprites.addSprite(sp);
    }

    /**
     * This function is responsible for initializing the game.
     * First we create a new GUI.
     * Then we create 2 balls and add them to the game.
     * Then we create multiple Blocks and add them to the game.
     * Finally, we create a Paddle and add it to the game.
     */
    public void initialize() {
        int width = this.runner.getGui().getDrawSurface().getWidth();
        int height = this.runner.getGui().getDrawSurface().getHeight();
        int xCountPos = width / 3;
        int xNamePos = (width / 3) * 2;
        int yPos = 15;
        this.sprites.addSprite(this.information.getBackground());
        int blockHeight = 20;
        int blockWidth = 25;
        int start = 0;
        Point topLeft = new Point(start, start);
        Rectangle rectangle = new Rectangle(topLeft, width, blockHeight);
        Block topBorder = new Block(rectangle, Color.GRAY);
        topBorder.addToGame(this);
        Point leftBlockTopLeft = new Point(start, blockHeight);
        Rectangle rectangle1 = new Rectangle(leftBlockTopLeft, blockWidth, height - blockHeight);
        Block leftBorder = new Block(rectangle1, Color.GRAY);
        leftBorder.addToGame(this);
        Point rightBlockTopLeft = new Point(width - blockWidth, blockHeight);
        Rectangle rectangle2 = new Rectangle(rightBlockTopLeft, blockWidth, height - blockHeight);
        Block rightBorder = new Block(rectangle2, Color.GRAY);
        rightBorder.addToGame(this);
        Point bottomBlockTopLeft = new Point(start, height - 1);
        Rectangle rectangle3 = new Rectangle(bottomBlockTopLeft, width, height - blockHeight);
        Block bottomBorder = new Block(rectangle3, Color.GRAY);
        bottomBorder.addToGame(this);
        bottomBorder.addHitListener(ballRemover);
        for (int i = 0; i < this.information.blocks().size(); i++) {
            Block block = this.information.blocks().get(i);
            block.addToGame(this);
            block.addHitListener(remover);
            block.addHitListener(score);
        }
        this.numOfBallsRemaining.setCurrentCount(this.information.numberOfBalls());
        this.numOfBlocksRemaining.setCurrentCount(this.information.numberOfBlocksToRemove());
        DrawSurface d = this.runner.getGui().getDrawSurface();
        int radius = 7;
        int whiteColor = 255;
        int ballDiff = 20;
        int xBaseBall = d.getWidth() / 2 - (this.information.numberOfBalls() / 2) * ballDiff;
        Color white = new Color(whiteColor, whiteColor, whiteColor);
        int index = 0;
        for (Velocity velocity : this.information.initialBallVelocities()) {
            int xStart = xBaseBall + ballDiff * index;
            int yStart = d.getHeight() - height / 6;
            Point center = new Point(xStart, yStart);
            Ball ball = new Ball(center, radius, white, this.environment);
            ball.addToGame(this);
            ball.setVelocity(velocity);
            index++;
        }
        int paddleSpeed = this.information.paddleSpeed();
        int paddleWidth = this.information.paddleWidth();
        int paddleHeight = 5;
        int x = d.getWidth() / 2;
        int y = d.getHeight() - paddleHeight * 3;
        Point topLeftPaddle = new Point(x - paddleWidth / 2, y);
        Rectangle rec = new Rectangle(topLeftPaddle, paddleWidth, paddleHeight);
        Paddle paddle = new Paddle(rec, Color.YELLOW, blockWidth,
                d.getWidth() - blockWidth, this.keyboard);
        paddle.addToGame(this);
    }

    /**
     * This function runs the game.
     * We move the ball 60 times per second and at each interval we check if a collision happened, if so we update
     * the ball location and its velocity.
     */
    public void run() {
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Remove a Collidable from the game.
     * @param c Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * Remove a Sprite from the game.
     * @param s Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Should the Animation continue running?
     * @return int.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This function draws one frame the GUI and moves all the balls on the screen and deals with collisions.
     * @param d DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        int countFrom = 3;
        int numOfSeconds = 3;
        // take care of the pause screen.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation("space", new PauseScreen(), runner));
        }
        // check if the game should end, no more balls or blocks.
        if (this.numOfBlocksRemaining.getValue() == 0 || this.numOfBallsRemaining.getValue() == 0) {
            this.running = false;
        } else {
            if (!countDownOver) { // if the count down animation is over.
                CountDownAnimation startScreen = new CountDownAnimation(sprites, numOfSeconds, countFrom);
                // run the count down animation.
                this.runner.run(startScreen);
                countDownOver = true;
            } else {
                this.sprites.drawAllOn(d);
                // checking for collisions and moving the balls.
                this.sprites.notifyAllTimePassed();
                // sleeping for some time to each interval in 33 milliseconds to have 60 FPS.
                this.numOfBlocksRemaining.setCurrentCount(remover.getRemainingBlocks().getValue());
            }
        }
    }

    /**
     * Return the list of Sprites in the level.
     * @return SpriteCollection.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Return number of Balls left.
     * @return int.
     */
    public int getNumOfBallsLeft() {
        return this.numOfBallsRemaining.getValue();
    }

    /**
     * Return number of blocks left.
     * @return int.
     */
    public int getNumOfBlocksLeft() {
        return this.numOfBlocksRemaining.getValue();
    }

    /**
     * Return the Score Tracking Listener.
     * @return ScoreTrackingListener.
     */
    public ScoreTrackingListener getScore() {
        return this.score;
    }
}
