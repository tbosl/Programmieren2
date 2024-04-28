package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ColorCycleManager;
import thd.gameobjects.base.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A gameobject used to represent the current score of the user.
 */
public class Score extends GameObject {
    private final String[] scoreBlockImages;
    private final ColorCycleManager colorCycleManager;
    private static final int COLOR_CYCLE_DURATION = 500;
    private static final int MARGIN_PER_NUMBER = 30;


    /**
     * Creates a Score with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the score.
     */
    public Score(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(200, 75);
        size = 1;
        colorCycleManager = new ColorCycleManager(gameView, COLOR_CYCLE_DURATION);
        scoreBlockImages = new String[]{
                ScoreBlockImages.ZERO,
                ScoreBlockImages.ONE,
                ScoreBlockImages.TWO,
                ScoreBlockImages.THREE,
                ScoreBlockImages.FOUR,
                ScoreBlockImages.FIVE,
                ScoreBlockImages.SIX,
                ScoreBlockImages.SEVEN,
                ScoreBlockImages.EIGHT,
                ScoreBlockImages.NINE};
    }

    @Override
    public String toString() {
        return "SCORE: " + position;
    }

    @Override
    public void addToCanvas() {
        addScoreNumbersToCanvas();
    }

    private void addScoreNumbersToCanvas() {
        List<Integer> scoreValues = createDigitsOfScoreInReversedOrder();
        for (int addedNumbers = 0; addedNumbers < scoreValues.size(); addedNumbers++) {
            int offset = addedNumbers * MARGIN_PER_NUMBER;
            gameView.addBlockImageToCanvas(generateColorizedNumber(scoreValues.get(addedNumbers)), position.getX() - offset, position.getY(), size, rotation);
        }
    }

    private List<Integer> createDigitsOfScoreInReversedOrder() {
        var digits = new ArrayList<Integer>();
        int score = gamePlayManager.getPoints();
        if (score == 0) {
            digits.add(0);
            digits.add(0);
            return digits;
        }
        while (score > 0) {
            digits.add(score % 10);
            score /= 10;
        }
        return digits;
    }

    private String generateColorizedNumber(int number) {
        int validatedNumber = number;
        if (number < 0 || number > 9) {
            System.err.printf("Invalid number for score: %d\n"
                              + "Default value (0) will be used.%n", number);
            validatedNumber = 0;
        }
        return scoreBlockImages[validatedNumber].replace('r', colorCycleManager.findCurrentColorCode());
    }
}
