package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShootingEnemyGameObject;

import java.awt.*;

/**
 * A gameobject that represents the alien invader called Mutant.
 */
class Mutant extends ShootingEnemyGameObject {
    private final MutantMovementPatterns movementPattern;
    private static final int SPACESHIP_DISTANCE_THRESHOLD = 30;
    private static final int POINTS_ON_DESTRUCTION = 150;


    /**
     * Creates a Mutant with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the mutant.
     * @param spaceship       The player spaceship - the target of the mutant.
     *                        (This will later come from GameManager or Astronaut)
     * @param preMutation     The lander which is mutated.
     */
    Mutant(GameView gameView, GamePlayManager gamePlayManager, Spaceship spaceship, Lander preMutation) {
        super(gameView, gamePlayManager, POINTS_ON_DESTRUCTION, Color.GRAY.darker());
        movementPattern = new MutantMovementPatterns(gameView);
        position.updateCoordinates(movementPattern.startPosition(preMutation.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position));
        size = 0.08;
        speedInPixel = 4;
        width = 25;
        height = 40;
        distanceToBackground = 1;
        int hitBoxOffsetX = 6;
        hitBoxOffsets(hitBoxOffsetX, 0, 0, 0);
    }

    @Override
    public String toString() {
        return "MUTANT: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position));
        position.moveToPosition(movementPattern.shake(spaceship.getPosition(), position), speedInPixel);
        if (position.distance(targetPosition) > SPACESHIP_DISTANCE_THRESHOLD) {
            position.moveToPosition(targetPosition, speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("mutant.png", position.getX(), position.getY(), size, rotation);
        System.out.println(this);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
    }
}

