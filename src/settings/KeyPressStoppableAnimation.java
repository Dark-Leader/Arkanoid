// ID: 318528189.

package settings;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * <p>
 *     The KeyPressStoppableAnimation represents an animation that will continue playing until the player
 *     presses a specific button.
 * </p>
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed = true;
    private AnimationRunner runner;
    private boolean stop = false;

    /**
     * Constructor.
     * @param key String.
     * @param animation Animation.
     * @param runner AnimationRunner.
     */
    public KeyPressStoppableAnimation(String key, Animation animation, AnimationRunner runner) {
        this.keyboard = runner.getGui().getKeyboardSensor();
        this.key = key;
        this.animation = animation;
        this.runner = runner;
    }

    /**
     * This function runs the animation in an infinite loop until the player presses a button.
     * @param d DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        while (true) {
            // run the animation.
            this.runner.run(animation);
            // if the button was already pressed before the animation started.
            if (keyboard.isPressed(key) && isAlreadyPressed) {
                continue;
            } else if (!keyboard.isPressed(key)) {
                // if the button is not pressed then next time the player presses it the animation will quit.
                isAlreadyPressed = false;
            } else if (keyboard.isPressed(key)) {
                // if the button is pressed stop the animation.
                stop = true;
                break;
            }
        }
    }

    /**
     * Should the animation continue running?
     * @return boolean.
     */
    public boolean shouldStop() {
        return stop;
    }
}
