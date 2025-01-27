package thd.gameobjects.movable;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShootingEnemyGameObject;

import java.awt.*;

/**
 * A gameobject that represents the alien invader called Swarmer.
 */
class Swarmer extends ShootingEnemyGameObject {
    private final SwarmerMovementPattern movementPattern;
    static final int MINIMUM_SWARMERS_PER_SWARM_EASY = 3;
    static final int MAXIMUM_SWARMERS_PER_SWARM_EASY = 4;

    static final int MINIMUM_SWARMERS_PER_SWARM_STANDARD = 5;
    static final int MAXIMUM_SWARMERS_PER_SWARM_STANDARD = 8;
    private static final int POINTS_ON_DESTRUCTION = 150;

    /**
     * The level of the enemy.
     */
    public static final int ENEMY_LEVEL = 3;

    /**
     * Creates a Swarmer with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the swarmer.
     * @param spaceship       The player spaceship - the target of the swarmer.
     * @param pod             The pod from which the swarmer is spawned.
     */
    Swarmer(GameView gameView, GamePlayManager gamePlayManager, Spaceship spaceship, Pod pod) {
        super(gameView, gamePlayManager, POINTS_ON_DESTRUCTION, Color.RED, 2000, 4000);
        size = 0.05;
        speedInPixel = Level.difficulty == Difficulty.EASY ? 5 : 8;
        width = 15;
        height = 15;
        movementPattern = new SwarmerMovementPattern(spaceship.getPosition(), width);
        position.updateCoordinates(movementPattern.startPosition(pod.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position, targetPosition));
        distanceToBackground = 1;
        int hitBoxOffsetX = 5;
        int hitBoxOffsetY = 5;
        hitBoxOffsets(hitBoxOffsetX, hitBoxOffsetY, 0, 0);
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

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
    }
}
