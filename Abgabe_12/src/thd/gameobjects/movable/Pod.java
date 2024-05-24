package thd.gameobjects.movable;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.EnemyGameObject;

import java.awt.*;

/**
 * A gameobject used to represent a Pod.
 */
public class Pod extends EnemyGameObject {
    private final PodMovementPattern podMovementPattern;
    private final Spaceship spaceship;
    private static final int POINTS_ON_DESTRUCTION = 1000;
    private static final int ANIMATION_INTERVALL = 300;

    private enum State {
        ROTATION_1("pod_animation_1.png"),
        ROTATION_2("pod_animation_2.png");

        private final String image;

        State(String image) {
            this.image = image;
        }
    }

    private State currentState;

    /**
     * The level of the enemy.
     */
    public static final int ENEMY_LEVEL = 3;

    /**
     * Creates a Pod with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the pod.
     */
    public Pod(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager, POINTS_ON_DESTRUCTION, Color.BLUE);
        this.spaceship = gamePlayManager.getSpaceship();
        podMovementPattern = new PodMovementPattern();
        position.updateCoordinates(podMovementPattern.startPosition(spaceship.getAbsolutePosition()));
        targetPosition.updateCoordinates(podMovementPattern.nextTargetPosition());
        size = 0.06;
        speedInPixel = 3;
        height = 20;
        width = 20;
        distanceToBackground = 1;
        int hitBoxOffset = 5;
        hitBoxOffsets(hitBoxOffset, hitBoxOffset, 0, 0);
        currentState = State.ROTATION_1;
    }

    @Override
    public String toString() {
        return "POD: " + position;
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(podMovementPattern.nextTargetPosition());
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (gameView.timer(ANIMATION_INTERVALL, this)) {
            switchToNextAnimationState();
        }
    }

    private void switchToNextAnimationState() {
        currentState = currentState == State.ROTATION_1 ? State.ROTATION_2 : State.ROTATION_1;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(currentState.image, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
        if (other instanceof LaserProjectile || other instanceof Spaceship) {
            int minimum = Level.difficulty == Difficulty.EASY ? Swarmer.MINIMUM_SWARMERS_PER_SWARM_EASY : Swarmer.MINIMUM_SWARMERS_PER_SWARM_STANDARD;
            int maximum = Level.difficulty == Difficulty.EASY ? Swarmer.MAXIMUM_SWARMERS_PER_SWARM_EASY : Swarmer.MAXIMUM_SWARMERS_PER_SWARM_STANDARD;
            int amountOfSwarmersToSpawn = random.nextInt(minimum, maximum);
            for (int spawnedSwarmers = 0; spawnedSwarmers < amountOfSwarmersToSpawn; spawnedSwarmers++) {
                gamePlayManager.spawnGameObject(new Swarmer(gameView, gamePlayManager, spaceship, this));
            }
        }
    }
}