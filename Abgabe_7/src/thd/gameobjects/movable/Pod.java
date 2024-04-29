package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;

import java.util.Random;

/**
 * A gameobject used to represent a Pod.
 */
public class Pod extends EnemyGameObject {
    private final RandomMovementPattern randomMovementPattern;
    private final Random random;
    private final Spaceship spaceship;
    private static final int POINTS_ON_DESTRUCTION = 1000;


    /**
     * Creates a Pod with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the pod.
     * @param spaceship       The player's spaceship.
     */
    public Pod(GameView gameView, GamePlayManager gamePlayManager, Spaceship spaceship) {
        super(gameView, gamePlayManager, POINTS_ON_DESTRUCTION);
        this.spaceship = spaceship;
        randomMovementPattern = new RandomMovementPattern();
        random = new Random();
        position.updateCoordinates(randomMovementPattern.startPosition());
        targetPosition.updateCoordinates(randomMovementPattern.nextTargetPosition());
        size = 0.06;
        speedInPixel = 3;
        height = 20;
        width = 20;
        distanceToBackground = 1;
        int hitBoxOffset = 5;
        hitBoxOffsets(hitBoxOffset, hitBoxOffset, 0, 0);
    }

    @Override
    public String toString() {
        return "POD: " + position;
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(randomMovementPattern.nextTargetPosition());
        }
        positionBeforeMoving.updateCoordinates(position);
        position.moveToPosition(targetPosition, speedInPixel);
        super.updatePosition();
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("pod_animation_1.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
        if (other instanceof LaserProjectile || other instanceof Spaceship) {
            int amountOfSwarmersToSpawn = random.nextInt(Swarmer.MINIMUM_SWARMERS_PER_SWARM, Swarmer.MAXIMUM_SWARMERS_PER_SWARM);
            for (int spawnedSwarmers = 0; spawnedSwarmers < amountOfSwarmersToSpawn; spawnedSwarmers++) {
                gamePlayManager.spawnGameObject(new Swarmer(gameView, gamePlayManager, spaceship, this));
            }
        }
    }
}