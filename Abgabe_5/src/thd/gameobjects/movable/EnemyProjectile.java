package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ColorCycleManager;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

/**
 * A gameobject that represents an enemy projectile.
 */
class EnemyProjectile extends Projectile {
    private final EnemyProjectileMovementPattern movementPattern;
    private final ColorCycleManager colorCycleManager;

    /**
     * Creates a laser projectile with a reference of the gameview.
     *
     * @param gameView          The GameView.
     * @param gamePlayManager   The manager which is responsible for the occurrence of the enemy projectile.
     * @param enemy             The enemy, from which the projectile got fired.
     * @param spaceshipPosition The position of the player's spaceship.
     */
    EnemyProjectile(GameView gameView, GamePlayManager gamePlayManager, GameObject enemy, Position spaceshipPosition) {
        super(gameView, gamePlayManager);
        movementPattern = new EnemyProjectileMovementPattern();
        colorCycleManager = new ColorCycleManager(gameView, 500);
        position.updateCoordinates(movementPattern.startPosition(enemy.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceshipPosition, enemy.getPosition()));
        rotation = 0;
        size = 15;
        speedInPixel = 10;
    }

    @Override
    public void updatePosition() {
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("X", position.getX(), position.getY(), size, true, colorCycleManager.findCurrentColor(), rotation);
    }

    @Override
    public String toString() {
        return "ENEMY PROJECTILE: " + position;
    }
}
