// ID : 318528189.

import interfaces.LevelInformation;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;
import settings.AnimationRunner;
import settings.GameFlow;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Arkanoid application is responsible for creating the game, and running it.
 * </p>
 */

public class Arkanoid {
    /**
     * This function is responsible for setting up and game and running it.
     * We receive a list of command line arguments, parse them into level numbers and run said levels in a loop
     * until the game is finished, either we won or lost.
     * in the end we close the GUI and exit.
     * @param args String[].
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner();
        List<LevelInformation> levels = new ArrayList<>();
        /*
        Parsing the levels we are going to play.
         */
        int amount = 0;
        for (String levelNumber : args) {
            try {
                int number = Integer.valueOf(levelNumber);
                if (number <= 0) {
                    continue;
                } else if (number > 4) {
                    continue;
                } else if (number == 1) {
                    levels.add(new DirectHit());
                    amount++;
                } else if (number == 2) {
                    levels.add(new WideEasy());
                    amount++;
                } else if (number == 3) {
                    levels.add(new Green3());
                    amount++;
                } else {
                    levels.add(new FinalFour());
                    amount++;
                }
            } catch (Exception e) {
                continue;
            }
        }
        if (amount == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
        // running the levels.
        GameFlow flow = new GameFlow(runner);
        flow.runLevels(levels);
        // closing the GUI.
        runner.getGui().close();
    }
}
