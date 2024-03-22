package thd.gameobjects.movable;

import thd.gameobjects.base.Position;
import thd.game.utilities.GameView;

import java.awt.*;

/**
 * A gameobject that represents the astronauts that get kidnapped by the alien invaders.
 */
public class Astronaut {
    private final GameView gameView;
    private final Position position;
    private double speedInPixel;
    private double rotation;
    private final double size;
    private final double width;
    private final double height;

    /**
     * Creates an Astronaut with a reference of the gameview.
     *
     * @param gameView The GameView
     */
    public Astronaut(GameView gameView) {
        this.gameView = gameView;
        size = 30;
        position = new Position(1100, 650);
        speedInPixel = 2;
        rotation = 0;
        width = 150;
        height = 33;
    }

    /**
     * Represent the astronaut as string.
     *
     * @return ASTRONAUT: current position
     */
    @Override
    public String toString() {
        return "ASTRONAUT: " + position;
    }

    /**
     * Changes the position of the object.
     */
    public void updatePosition() {
        position.left(speedInPixel);
    }

    /**
     * Adds the corresponding object to the canvas.
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("astronaut.png", position.getX(), position.getY(), 0.08, rotation);
    }
}
