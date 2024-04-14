package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

/**
 * A gameobject that represents the alien invader called Swarmer.
 */
public class Swarmer extends GameObject {
    private final SwarmerMovementPattern movementPattern;
    private final Spaceship spaceship;


    /**
     * Creates a Swarmer with a reference of the gameview.
     *
     * @param gameView  The GameView.
     * @param spaceship The player spaceship - the target of the mutant.
     *                  (This will later come from GameManager or Astronaut)
     * @param pod       The pod from which the swarmer is spawned.
     */
    public Swarmer(GameView gameView, Spaceship spaceship, Pod pod) {
        super(gameView);
        this.spaceship = spaceship;
        movementPattern = new SwarmerMovementPattern(spaceship.getPosition());
        position.updateCoordinates(movementPattern.startPosition(pod.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position, targetPosition));
        rotation = 0;
        size = 0.06;
        speedInPixel = 8;
        width = 50;
        height = 50;
    }

    @Override
    public String toString() {
        return "SWARMER: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position, targetPosition));
        position.moveToPosition(targetPosition, speedInPixel);

    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("swarmer.png", position.getX(), position.getY(), size, rotation);
    }
}
