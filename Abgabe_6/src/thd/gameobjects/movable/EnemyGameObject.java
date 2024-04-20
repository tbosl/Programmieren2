package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

import java.util.List;

/**
 * The parent class of all enemies.
 */
public abstract class EnemyGameObject extends CollidingGameObject {
    private List<CollidingGameObject> collidingGameObjectsForPathDecision;
    protected final Position positionBeforeMoving;
    private final int pointsOnDestruction;

    EnemyGameObject(GameView gameView, GamePlayManager gamePlayManager, int pointsOnDestruction) {
        super(gameView, gamePlayManager);
        collidingGameObjectsForPathDecision = gamePlayManager.provideAllActiveEnemies();
        positionBeforeMoving = new Position();
        this.pointsOnDestruction = pointsOnDestruction;
    }

    @Override
    public void updatePosition() {
        collidingGameObjectsForPathDecision = gamePlayManager.provideAllActiveEnemies();
        for (CollidingGameObject collidingGameObject : collidingGameObjectsForPathDecision) {
            if (collidingGameObject == this) {
                continue;
            }
            if (collidesWith(collidingGameObject)) {
//                double speedFactor = 1.5;
//                double offsetX = collidingGameObject.getPosition().getX() < position.getX() ? speedInPixel : -speedInPixel;
//                double offsetY = collidingGameObject.getPosition().getY() > position.getY() ? speedInPixel : -speedInPixel;
//                position.updateCoordinates(positionBeforeMoving);
//                position.down(speedFactor * offsetY);
//                position.right(speedFactor * offsetX);
//                break;
            }
        }
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
