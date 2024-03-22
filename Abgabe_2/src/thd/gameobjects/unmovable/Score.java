package thd.gameobjects.unmovable;

import thd.gameobjects.base.Position;
import thd.game.utilities.GameView;

import java.awt.*;

/**
 * A gameobject used to represent the current score of the user.
 */
public class Score {
    private final GameView gameView;
    private final Position position;
    private double speedInPixel;
    private double rotation;
    private final double size;
    private final double width;
    private final double height;
    private final ScoreBlockImages scoreBlockImages;

    /**
     * Creates a Score with a reference of the gameview.
     *
     * @param gameView The GameView
     */
    public Score(GameView gameView) {
        this.gameView = gameView;
        size = 30;
        speedInPixel = 0;
        rotation = 0;
        width = 150;
        height = 33;
        position = new Position(GameView.WIDTH - width, 3);
        scoreBlockImages = new ScoreBlockImages();
    }

    /**
     * Represent the score as string.
     *
     * @return SCORE: current position
     */
    @Override
    public String toString() {
        return "SCORE: " + position;
    }

    /**
     * Adds the corresponding object to the canvas.
     */
    public void addToCanvas() {
        addScoreToCanvas();
    }

    private void addScoreToCanvas() {
        gameView.addBlockImageToCanvas(scoreBlockImages.generateOne('r'), position.getX(), position.getY(), 1, rotation);
        gameView.addBlockImageToCanvas(scoreBlockImages.generateFive('r'), position.getX() + 30, position.getY(), 1, rotation);
        gameView.addBlockImageToCanvas(scoreBlockImages.generateZero('r'), position.getX() + 60, position.getY(), 1, rotation);
        gameView.addBlockImageToCanvas(scoreBlockImages.generateZero('r'), position.getX() + 90, position.getY(), 1, rotation);
    }
}
