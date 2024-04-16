package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject that represents the alien invader called Baiter.
 */
public class Baiter extends GameObject {
    private final BaiterMovementPattern movementPattern;
    private final Spaceship spaceship;
    private static final int THRESHOLD_TO_SPACESHIP = 200;
    private static final int SLOW_SPEED_IN_PIXEL = 5;
    private static final int FAST_SPEED_IN_PIXEL = 8;


    /**
     * Creates a Baiter with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the baiter.
     * @param spaceship       The player spaceship - the target of the mutant.
     */
    public Baiter(GameView gameView, GamePlayManager gamePlayManager, Spaceship spaceship) {
        super(gameView, gamePlayManager);
        this.spaceship = spaceship;
        movementPattern = new BaiterMovementPattern();
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition()));
        size = 0.08;
        setupSpeedInPixelDependingOnDistanceToSpaceship();
        width = 50;
        height = 50;
    }

    @Override
    public String toString() {
        return "BAITER: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition()));
        setupSpeedInPixelDependingOnDistanceToSpaceship();
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("baiter.png", position.getX(), position.getY(), size, rotation);
    }

    private void setupSpeedInPixelDependingOnDistanceToSpaceship() {
        speedInPixel = position.distance(spaceship.getPosition()) < THRESHOLD_TO_SPACESHIP ? FAST_SPEED_IN_PIXEL : SLOW_SPEED_IN_PIXEL;
    }
}
