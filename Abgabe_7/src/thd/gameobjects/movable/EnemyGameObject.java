package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

/**
 * The parent class of all enemies.
 */
public abstract class EnemyGameObject extends CollidingGameObject {
    protected final Position positionBeforeMoving;
    private final int pointsOnDestruction;

    EnemyGameObject(GameView gameView, GamePlayManager gamePlayManager, int pointsOnDestruction) {
        super(gameView, gamePlayManager);
        positionBeforeMoving = new Position();
        this.pointsOnDestruction = pointsOnDestruction;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        boolean isLaserProjectile = other instanceof LaserProjectile;
        if (isLaserProjectile || other instanceof Spaceship) {
            gamePlayManager.destroyGameObject(this);
            if (isLaserProjectile) {
                gamePlayManager.addPoints(pointsOnDestruction);
            }
        }
    }

    int getPointsOnDestruction() {
        return pointsOnDestruction;
    }
}
