package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.BaseScore;

import java.awt.*;
import java.util.List;

/**
 * A gameobject used to represent the current high score.
 */
public class HighScore extends BaseScore {
    private final int score;

    /**
     * Creates a Score with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the score.
     * @param score           The high score which will be shown.
     */
    public HighScore(GameView gameView, GamePlayManager gamePlayManager, int score) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(1030, 15);
        this.score = score;
    }

    @Override
    public void addToCanvas() {
        int stringSize = 28;
        gameView.addTextToCanvas("HIGH:", position.getX(), position.getY(), stringSize, true, Color.YELLOW, rotation);
        addScoreNumbersToCanvas();
    }

    protected void addScoreNumbersToCanvas() {
        List<Integer> scoreValues = createDigitsOfScoreInReversedOrder(score);
        int defaultOffset = 220;
        int yOffset = 10;
        for (int addedNumbers = 0; addedNumbers < scoreValues.size(); addedNumbers++) {
            int offset = addedNumbers * MARGIN_PER_NUMBER - defaultOffset;
            gameView.addBlockImageToCanvas(generateNumberBlockImage(scoreValues.get(addedNumbers)), position.getX() - offset, position.getY() + yOffset, size, rotation);
        }
    }

    private String generateNumberBlockImage(int number) {
        int validatedNumber = number;
        if (number < 0 || number > 9) {
            System.err.printf("Invalid number for score: %d\n"
                              + "Default value (0) will be used.%n", number);
            validatedNumber = 0;
        }
        return scoreBlockImages.get(validatedNumber).replace('r', 'y');
    }

    @Override
    public String toString() {
        return "SCORE: " + position;
    }
}
