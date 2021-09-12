// ID: 318528189.

package settings;
import interfaces.LevelInformation;
import sprites.ScoreIndicator;
import java.util.List;

/**
 * <p>
 *     The GameFlow application creates a game by creating a game and running the levels in a loop while the player
 *     didn't lose.
 * </p>
 */
public class GameFlow {
    private AnimationRunner runner;
    private boolean lost = false;

    /**
     * Constructor.
     * @param ar AnimationRunner.
     */
    public GameFlow(AnimationRunner ar) {
        this.runner = ar;
    }

    /**
     * This function goes over the levels in a loop while the player didn't lose.
     * @param levels List<LevelInformation>.
     */
    public void runLevels(List<LevelInformation> levels) {
        int width = this.runner.getGui().getDrawSurface().getWidth();
        int height = this.runner.getGui().getDrawSurface().getHeight();
        int xCountPos = width / 3;
        int xNamePos = (width / 3) * 2;
        int y = 15;
        // creating a counter for the score.
        Counter scoreCounter = new Counter(0);
        ScoreIndicator score = new ScoreIndicator(scoreCounter, xCountPos, y, xNamePos);
        int passedLevel = 100;
        /*
        Iterating over the levels provided and creating game levels and playing them as long as the player
        didn't lose.
         */
        for (LevelInformation levelInfo : levels) {
            // create a game level.
            GameLevel level = new GameLevel(this.runner, levelInfo, scoreCounter);
            // initialize the level.
            level.initialize();
            // updating the score and adding it to the sprites to draw it as well.
            score.setLevelName("Level Name: " + levelInfo.levelName());
            level.addSprite(score);
            /*
            while the player didn't or the level didn't end yet.
             */
            while (level.getNumOfBallsLeft() > 0 && level.getNumOfBlocksLeft() > 0) {
                level.run();
            }
            /*
            if the player lost.
             */
            if (level.getNumOfBallsLeft() == 0 && level.getNumOfBlocksLeft() > 0) {
                this.lost = true;
                break;
            } else {
                // the player passed a level.
                score.getCounter().increase(passedLevel);
            }
        }
        String message;
        if (lost) {
            message = "Game Over!";
        } else {
            message = "You Won!";
        }
        String key = "space";
        this.runner.run(new KeyPressStoppableAnimation(key,
                new EndScreen(message, width / 6, height / 2, scoreCounter.getValue()), this.runner));
    }
}
