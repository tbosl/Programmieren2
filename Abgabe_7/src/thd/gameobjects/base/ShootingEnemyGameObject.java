package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.EnemyGameObject;
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
    private static final int LOWER_INTERVALL_BOUND = 1000;
    private static final int UPPER_INTERVALL_BOUND = 2000;

    /**
     * Creates a new instance of a shooting enemy.
     *
     * @param gameView           The gameview.
     * @param gamePlayManager    The gameplay manager.
     * @param pointOnDestruction The amount of points, the score will be increased after the enemy is shot.
     * @param scanColor          The color in which the enemy will appear in the scan area.
     */
    public ShootingEnemyGameObject(GameView gameView, GamePlayManager gamePlayManager, int pointOnDestruction, Color scanColor) {
        super(gameView, gamePlayManager, pointOnDestruction, scanColor);
        spaceship = gamePlayManager.getSpaceship();
        random = new Random();
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(currentShootIntervallInMilliseconds, this)) {
            shoot();
            currentShootIntervallInMilliseconds = generateNewShootIntervall();
        }
    }

    private void shoot() {
        gamePlayManager.spawnGameObject(new EnemyProjectile(gameView, gamePlayManager, this, spaceship.getPosition()));
    }

    private int generateNewShootIntervall() {
        return random.nextInt(LOWER_INTERVALL_BOUND, UPPER_INTERVALL_BOUND);
    }
}
