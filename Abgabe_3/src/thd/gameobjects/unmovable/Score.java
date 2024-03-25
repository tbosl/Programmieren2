package thd.gameobjects.unmovable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject used to represent the current score of the user.
 */
public class Score extends GameObject {
    private final char[] colors;
    private int colorIndex;
    private final String[] scoreBlockImages;

    /**
     * Creates a Score with a reference of the gameview.
     *
     * @param gameView The GameView
     */
    public Score(GameView gameView) {
        super(gameView);
        position.updateCoordinates(GameView.WIDTH - 120, 3);
        rotation = 0;
        size = 1;
        colors = new char[]{'r', 'P', 'B', 'b', 'C', 'W', 'y', 'Y', 'O'};
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
        updateColor();
        addScoreNumbersToCanvas();
    }

    private void addScoreNumbersToCanvas() {
        gameView.addBlockImageToCanvas(generateColorizedNumber(1), position.getX(), position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(generateColorizedNumber(5), position.getX() + 30, position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(generateColorizedNumber(0), position.getX() + 60, position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(generateColorizedNumber(0), position.getX() + 90, position.getY(), size, rotation);
    }

    private void updateColor() {
        if (gameView.timer(500, this)) {
            colorIndex++;
        }
        if (colorIndex > colors.length - 1) {
            colorIndex = 0;
        }
    }

    private String generateColorizedNumber(int number) {
        int validatedNumber = number;
        if (number < 0 || number > 9) {
            System.err.println(String.format("Invalid number for score: %d\n"
                                             + "Default value (0) will be used.", number));
            validatedNumber = 0;
        }
        return scoreBlockImages[validatedNumber].replace('r', colors[colorIndex]);
    }
}
