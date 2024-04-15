package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.unmovable.BomberBomb;

import java.util.Random;

/**
 * A gameobject that represents the alien invader called Bomber.
 */
public class Bomber extends GameObject {
    private final BomberMovementPattern movementPattern;
    private int currentBombDroppingIntervallInMilliseconds;
    private final Random random;
    private static final int LOWER_INTERVALL_BOUND = 4000;
    private static final int UPPER_INTERVAL_BOUND = 8000;

    /**
     * Creates a Bomber with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the game object.
     */
    public Bomber(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        movementPattern = new BomberMovementPattern();
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(position));
        random = new Random();
        currentBombDroppingIntervallInMilliseconds = generateNewBombDroppingIntervall();
        size = 0.1;
        speedInPixel = 2;
        width = 50;
        height = 50;
    }

    @Override
    public String toString() {
        return "BOMBER: " + position;
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(movementPattern.nextTargetPosition(position));
        }
        double sinusScale = 0.5;
        position.down(Math.round(Math.sin(sinusScale * Math.toRadians(position.getX()))));
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(currentBombDroppingIntervallInMilliseconds, this)) {
            gamePlayManager.spawnGameObject(new BomberBomb(gameView, gamePlayManager, this));
            currentBombDroppingIntervallInMilliseconds = generateNewBombDroppingIntervall();
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("bomber_animation_1.png", position.getX(), position.getY(), size, rotation);
    }

    private int generateNewBombDroppingIntervall() {
        return random.nextInt(LOWER_INTERVALL_BOUND, UPPER_INTERVAL_BOUND);
    }
}
