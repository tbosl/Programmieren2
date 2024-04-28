package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;

import java.util.List;
import java.util.Random;

/**
 * A gameobject that represents the alien invader called Mutant.
 */
class Mutant extends CollidingGameObject {
    private final MutantMovementPatterns movementPattern;
    private final Spaceship spaceship;
    private final Random random;
    private int currentDoubleShootIntervallInMilliseconds;
    private static final int LOWER_INTERVALL_BOUND = 1000;
    private static final int UPPER_INTERVALL_BOUND = 2000;
    private static final int SPACESHIP_DISTANCE_THRESHOLD = 30;
    private List<CollidingGameObject> collidingGameObjectsForPathDecision;
    private static final int POINTS_ON_DESTRUCTION = 150; // TODO Implement with Abgabe_7


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
        super(gameView, gamePlayManager);
        this.spaceship = spaceship;
        movementPattern = new MutantMovementPatterns(gameView);
        random = new Random();
        position.updateCoordinates(movementPattern.startPosition(preMutation.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position));
        currentDoubleShootIntervallInMilliseconds = generateNewShootIntervall();
        collidingGameObjectsForPathDecision = gamePlayManager.provideAllActiveEnemies();
        size = 0.08;
        speedInPixel = 4;
        width = 25;
        height = 40;
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
    public void updateStatus() {
        if (gameView.timer(currentDoubleShootIntervallInMilliseconds, this)) {
            fire();
            currentDoubleShootIntervallInMilliseconds = generateNewShootIntervall();
        }
    }

    private void fire() {
        gamePlayManager.spawnGameObject(new EnemyProjectile(gameView, gamePlayManager, this, spaceship.getPosition()));
    }

    private int generateNewShootIntervall() {
        return random.nextInt(LOWER_INTERVALL_BOUND, UPPER_INTERVALL_BOUND);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("mutant.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        // TODO Change back to EnemyGameObject after Wichtel changed.
        if (other instanceof LaserProjectile) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}

