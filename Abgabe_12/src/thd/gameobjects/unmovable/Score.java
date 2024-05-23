package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.BaseScore;
import thd.gameobjects.base.ColorCycleManager;

import java.util.List;

/**
 * A gameobject used to represent the current score of the user.
 */
public class Score extends BaseScore {
    private final ColorCycleManager colorCycleManager;
    private static final int COLOR_CYCLE_DURATION = 500;


    /**
     * Creates a Score with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the score.
     */
    public Score(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(200, 75);
        colorCycleManager = new ColorCycleManager(gameView, COLOR_CYCLE_DURATION);
    }

    @Override
    public String toString() {
        return "SCORE: " + position;
    }

    @Override
    public void addToCanvas() {
        addScoreNumbersToCanvas(gamePlayManager.getPoints());
    }

    private void addScoreNumbersToCanvas(int score) {
        List<Integer> scoreValues = createDigitsOfScoreInReversedOrder(score);
        for (int addedNumbers = 0; addedNumbers < scoreValues.size(); addedNumbers++) {
            int offset = addedNumbers * MARGIN_PER_NUMBER;
            gameView.addBlockImageToCanvas(generateColorizedNumber(scoreValues.get(addedNumbers)), position.getX() - offset, position.getY(), size, rotation);
        }
    }

    private String generateColorizedNumber(int number) {
        int validatedNumber = number;
        if (number < 0 || number > 9) {
            System.err.printf("Invalid number for score: %d\n"
                              + "Default value (0) will be used.%n", number);
            validatedNumber = 0;
        }
        return scoreBlockImages.get(validatedNumber).replace('r', colorCycleManager.findCurrentColorCode());
    }
}
