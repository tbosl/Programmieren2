package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject that represents the alien invader called Baiter.
 */
public class Baiter extends GameObject {
    private final BaiterMovementPattern movementPattern;
    private final Spaceship spaceship;
    private final int thresholdToSpaceship;


    /**
     * Creates a Baiter with a reference of the gameview.
     *
     * @param gameView  The GameView.
     * @param spaceship The player spaceship - the target of the mutant.
     */
    public Baiter(GameView gameView, Spaceship spaceship) {
        super(gameView);
        this.spaceship = spaceship;
        movementPattern = new BaiterMovementPattern();
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition()));
        rotation = 0;
        size = 0.08;
        speedInPixel = 6;
        width = 50;
        height = 50;
        thresholdToSpaceship = 200;
    }

    @Override
    public String toString() {
        return "BAITER: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition()));
        speedInPixel = position.distance(spaceship.getPosition()) < thresholdToSpaceship ? 7 : 5;
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("baiter.png", position.getX(), position.getY(), size, rotation);
    }
}
