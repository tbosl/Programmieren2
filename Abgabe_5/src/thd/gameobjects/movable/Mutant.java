package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.util.Random;

/**
 * A gameobject that represents the alien invader called Mutant.
 */
class Mutant extends GameObject {
    private final MutantMovementPatterns movementPattern;
    private final Spaceship spaceship;
    private final Random random;
    private int currentDoubleShootIntervallInMilliseconds;
    private static final int LOWER_INTERVALL_BOUND = 1000;
    private static final int UPPER_INTERVALL_BOUND = 2000;


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
        position.updateCoordinates(movementPattern.startPosition(preMutation.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position));
        random = new Random();
        currentDoubleShootIntervallInMilliseconds = generateNewShootIntervall();
        rotation = 0;
        size = 0.08;
        speedInPixel = 4;
        width = 50;
        height = 50;
    }

    @Override
    public String toString() {
        return "MUTANT: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position));
        position.moveToPosition(movementPattern.shake(spaceship.getPosition(), position), 4);
        if (position.distance(targetPosition) > 30) {
            position.moveToPosition(targetPosition, speedInPixel);
        }
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(currentDoubleShootIntervallInMilliseconds, this)) {
            gamePlayManager.spawnGameObject(new EnemyProjectile(gameView, gamePlayManager, this, spaceship.getPosition()));
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
}

