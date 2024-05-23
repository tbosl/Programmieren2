package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;

import java.util.ArrayList;
import java.util.List;

/**
 * A gameobject used as a base for current score of the user and the high score.
 */
public abstract class BaseScore extends GameObject {
    protected final List<String> scoreBlockImages;
    protected static final int MARGIN_PER_NUMBER = 30;


    /**
     * Crates a new GameObject.
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager The manager which is responsible for the occurrence of the game object.
     */
    public BaseScore(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        distanceToBackground = 0;
        size = 1;
        scoreBlockImages = new ArrayList<>(List.of(
                ScoreBlockImages.ZERO,
                ScoreBlockImages.ONE,
                ScoreBlockImages.TWO,
                ScoreBlockImages.THREE,
                ScoreBlockImages.FOUR,
                ScoreBlockImages.FIVE,
                ScoreBlockImages.SIX,
                ScoreBlockImages.SEVEN,
                ScoreBlockImages.EIGHT,
                ScoreBlockImages.NINE));
    }

    protected List<Integer> createDigitsOfScoreInReversedOrder(int score) {
        var digits = new ArrayList<Integer>();
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
}
