package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject that represents the astronauts that get kidnapped by the alien invaders.
 */
public class Astronaut extends GameObject {
    private final AstronautMovementPatterns astronautMovementPatterns;

    /**
     * Creates an Astronaut with a reference of the gameview.
     *
     * @param gameView The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the astronaut.
     */
    public Astronaut(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        astronautMovementPatterns = new AstronautMovementPatterns();
        position.updateCoordinates(astronautMovementPatterns.startPosition());
        targetPosition.updateCoordinates(astronautMovementPatterns.nextTargetPosition(position));
        rotation = 0;
        size = 0.08;
        speedInPixel = 0.25;
    }

    @Override
    public String toString() {
        return "ASTRONAUT: " + position;
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(astronautMovementPatterns.nextTargetPosition(position));
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("astronaut.png", position.getX(), position.getY(), size, rotation);
    }
}
