package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.EnemyProjectile;
import thd.gameobjects.movable.Spaceship;

import java.awt.*;
import java.util.Random;

/**
 * Represents an enemy game object with the ability to shoot.
 */
public abstract class ShootingEnemyGameObject extends EnemyGameObject {
    private final Spaceship spaceship;
    private int currentShootIntervallInMilliseconds;
    private final Random random;
    private static final int DEFAULT_LOWER_INTERVALL_BOUND = 1000;
    private static final int DEFAULT_UPPER_INTERVALL_BOUND = 2000;
    private final int lowerIntervallBound;
    private final int upperIntervallBound;

    /**
     * Creates a new instance of a shooting enemy.
     *
     * @param gameView           The gameview.
     * @param gamePlayManager    The gameplay manager.
     * @param pointOnDestruction The amount of points, the score will be increased after the enemy is shot.
     * @param scanColor          The color in which the enemy will appear in the scan area.
     */
    public ShootingEnemyGameObject(GameView gameView, GamePlayManager gamePlayManager, int pointOnDestruction, Color scanColor) {
        this(gameView, gamePlayManager, pointOnDestruction, scanColor, DEFAULT_LOWER_INTERVALL_BOUND, DEFAULT_UPPER_INTERVALL_BOUND);
    }

    /**
     * Creates a new instance of a shooting enemy.
     *
     * @param gameView            The gameview.
     * @param gamePlayManager     The gameplay manager.
     * @param pointOnDestruction  The amount of points, the score will be increased after the enemy is shot.
     * @param scanColor           The color in which the enemy will appear in the scan area.
     * @param lowerIntervallBound The lower intervall bound of the shoot timer.
     * @param upperIntervallBound The upper intervall bound of the shoot timer.
     */
    public ShootingEnemyGameObject(GameView gameView, GamePlayManager gamePlayManager, int pointOnDestruction, Color scanColor, int lowerIntervallBound, int upperIntervallBound) {
        super(gameView, gamePlayManager, pointOnDestruction, scanColor);
        spaceship = gamePlayManager.getSpaceship();
        random = new Random();
        this.lowerIntervallBound = lowerIntervallBound;
        this.upperIntervallBound = upperIntervallBound;
        generateNewShootIntervall();
    }


    @Override
    public void updateStatus() {
        if (gameView.timer(currentShootIntervallInMilliseconds, this)) {
            shoot();
            generateNewShootIntervall();
        }
    }

    private void shoot() {
        gamePlayManager.spawnGameObject(new EnemyProjectile(gameView, gamePlayManager, this, spaceship.getPosition()));
    }

    private void generateNewShootIntervall() {
        currentShootIntervallInMilliseconds = random.nextInt(lowerIntervallBound, upperIntervallBound);
    }
}
