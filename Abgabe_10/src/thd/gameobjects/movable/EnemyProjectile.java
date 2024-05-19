package thd.gameobjects.movable;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

/**
 * A gameobject that represents an enemy projectile.
 */
public class EnemyProjectile extends Projectile {
    private final ColorCycleManager colorCycleManager;
    private static final int COLOR_CYCLE_DURATION = 500;

    /**
     * Creates a laser projectile with a reference of the gameview.
     *
     * @param gameView          The GameView.
     * @param gamePlayManager   The manager which is responsible for the occurrence of the enemy projectile.
     * @param enemy             The enemy, from which the projectile got fired.
     * @param spaceshipPosition The position of the player's spaceship.
     */
    public EnemyProjectile(GameView gameView, GamePlayManager gamePlayManager, GameObject enemy, Position spaceshipPosition) {
        super(gameView, gamePlayManager);
        EnemyProjectileMovementPattern movementPattern = new EnemyProjectileMovementPattern();
        colorCycleManager = new ColorCycleManager(gameView, COLOR_CYCLE_DURATION);
        position.updateCoordinates(movementPattern.startPosition(enemy.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceshipPosition, enemy.getPosition()));
        size = 0.08;
        speedInPixel = Level.difficulty == Difficulty.EASY ? 8 : 10;
        width = 25;
        height = 5;
        distanceToBackground = 1;
        hitBoxOffsets(0, 0, 0, 0);
    }

    @Override
    public void updatePosition() {
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("enemy_laser.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "ENEMY PROJECTILE: " + position;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Spaceship) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
