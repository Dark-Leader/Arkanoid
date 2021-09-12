// ID: 318528189.

package settings;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * <p>
 *    The AnimationRunner application is in charge of running an animation on the GUI provided.
 * </p>
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     */
    public AnimationRunner() {
        int width = 800;
        int height = 600;
        int fps = 60;
        this.gui = new GUI("Arkanoid", width, height);
        this.framesPerSecond = fps;
        this.sleeper = new Sleeper();
    }

    /**
     * This function is responsible of running the animation provided.
     * @param animation Animation.
     */
    public void run(Animation animation) {
        // setting the FPS to 60.
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) { // while the animation didn't finish.
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            // draw one frame from the animation.
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Return the GUI.
     * @return biuoop.GUI.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Return number of frames per second.
     * @return int.
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     * Return the sleeper.
     * @return biuoop.Sleeper.
     */
    public Sleeper getSleeper() {
        return sleeper;
    }
}
