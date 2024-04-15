package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ColorCycleManager;
import thd.gameobjects.base.GameObject;

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
        position.updateCoordinates(100, 75);
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
        int[] demoNumbers = {1, 5, 0, 0};
        for (int addedNumbers = 0; addedNumbers < demoNumbers.length; addedNumbers++) {
            int offset = addedNumbers * MARGIN_PER_NUMBER;
            gameView.addBlockImageToCanvas(generateColorizedNumber(demoNumbers[addedNumbers]), position.getX() + offset, position.getY(), size, rotation);
        }
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
