package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

/**
 * A gameobject that represents the first type of alien invader.
 */
public class Lander {
    private final GameView gameView;
    private final Position position;
    private double speedInPixel;
    private double rotation;
    private final double size;
    private final double width;
    private final double height;

    /**
     * Creates a Lander with a reference of the gameview.
     *
     * @param gameView The GameView
     */
    public Lander(GameView gameView) {
        this.gameView = gameView;
        size = 30;
        position = new Position(0, GameView.HEIGHT / 2);
        speedInPixel = 5;
        rotation = 0;
        width = 150;
        height = 33;
    }

    /**
     * Represent the lander as string.
     *
     * @return LANDER: current position
     */
    @Override
    public String toString() {
        return "LANDER: " + position;
    }

    /**
     * Changes the position of the object.
     */
    public void updatePosition() {
        position.right(speedInPixel);
        rotation++;
    }

    /**
     * Adds the corresponding object to the canvas.
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("lander.png", position.getX(), position.getY(), 2, rotation);
    }
}
