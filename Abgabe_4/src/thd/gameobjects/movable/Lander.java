package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject that represents the first type of alien invader.
 */
public class Lander extends GameObject {
    private final LanderMovementPattern landerMovementPattern;
    private final Astronaut nearestAstronaut;


    /**
     * Creates a Lander with a reference of the gameview.
     *
     * @param gameView         The GameView
     * @param nearestAstronaut The nearest astronaut - the target of the lander.
     *                         (This will later come from GameManager or Astronaut)
     */
    public Lander(GameView gameView, Astronaut nearestAstronaut) {
        super(gameView);
        this.nearestAstronaut = nearestAstronaut;
        landerMovementPattern = new LanderMovementPattern();
        position.updateCoordinates(landerMovementPattern.startPosition());
        targetPosition.updateCoordinates(landerMovementPattern.nextTargetPosition(nearestAstronaut.getPosition(), position));
        rotation = 0;
        size = 0.08;
        speedInPixel = 5;
        width = 50;
        height = 50;
    }

    @Override
    public String toString() {
        return "LANDER: " + position;
    }

    @Override
    public void updatePosition() {
        if (targetPosition.getY() != 0) {
            targetPosition.updateCoordinates(landerMovementPattern.nextTargetPosition(nearestAstronaut.getPosition(), position));
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("lander.png", position.getX(), position.getY(), size, rotation);
    }
}
