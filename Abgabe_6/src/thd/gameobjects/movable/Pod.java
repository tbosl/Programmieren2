package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.util.Random;

/**
 * A gameobject used to represent a Pod.
 */
public class Pod extends GameObject {
    private final RandomMovementPattern randomMovementPattern;
    private final Random random;
    private final Spaceship spaceship;

    /**
     * Creates a Pod with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the pod.
     * @param spaceship       The player's spaceship.
     */
    public Pod(GameView gameView, GamePlayManager gamePlayManager, Spaceship spaceship) {
        super(gameView, gamePlayManager);
        this.spaceship = spaceship;
        randomMovementPattern = new RandomMovementPattern();
        random = new Random();
        position.updateCoordinates(randomMovementPattern.startPosition());
        targetPosition.updateCoordinates(randomMovementPattern.nextTargetPosition());
        size = 0.06;
        speedInPixel = 3;
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
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void updateStatus() {
        // TODO Remove when collision available
        if (gameView.timer(5000, this)) {
            gamePlayManager.destroyGameObject(this);
            int amountOfSwarmersToSpawn = random.nextInt(Swarmer.MINIMUM_SWARMERS_PER_SWARM, Swarmer.MAXIMUM_SWARMERS_PER_SWARM);
            for (int spawnedSwarmers = 0; spawnedSwarmers < amountOfSwarmersToSpawn; spawnedSwarmers++) {
                gamePlayManager.spawnGameObject(new Swarmer(gameView, gamePlayManager, spaceship, this));
            }
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("pod_animation_1.png", position.getX(), position.getY(), size, rotation);
    }
}