package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShootingEnemyGameObject;

import java.awt.*;

/**
 * A gameobject that represents the alien invader called Baiter.
 */
public class Baiter extends ShootingEnemyGameObject {
    private final BaiterMovementPattern movementPattern;
    private static final int THRESHOLD_TO_SPACESHIP = 200;
    private static final int SLOW_SPEED_IN_PIXEL = 4;
    private static final int FAST_SPEED_IN_PIXEL = 6;
    private static final int POINTS_ON_DESTRUCTION = 200;

    /**
     * Creates a Baiter with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the baiter.
     */
    public Baiter(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager, POINTS_ON_DESTRUCTION, Color.GREEN);
        movementPattern = new BaiterMovementPattern();
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition()));
        size = 0.08;
        setupSpeedInPixelDependingOnDistanceToSpaceship();
        width = 40;
        height = 16;
        distanceToBackground = 1;
        int hitBoxOffsetX = 3;
        int hitBoxOffsetY = 2;
        hitBoxOffsets(hitBoxOffsetX, hitBoxOffsetY, 0, 0);
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

    private void setupSpeedInPixelDependingOnDistanceToSpaceship() {
        speedInPixel = position.distance(spaceship.getPosition()) < THRESHOLD_TO_SPACESHIP ? FAST_SPEED_IN_PIXEL : SLOW_SPEED_IN_PIXEL;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("baiter.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
    }
}
