package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ScannedGameObject;
import thd.gameobjects.base.ShiftableGameObject;

import java.awt.*;

/**
 * The parent class of all enemies.
 */
public abstract class EnemyGameObject extends ScannedGameObject implements ShiftableGameObject {
    private final int pointsOnDestruction;

    EnemyGameObject(GameView gameView, GamePlayManager gamePlayManager, int pointsOnDestruction, Color scanColor) {
        super(gameView, gamePlayManager, scanColor);
        this.pointsOnDestruction = pointsOnDestruction;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        boolean isLaserProjectile = other instanceof LaserProjectile;
        if (isLaserProjectile || other instanceof Spaceship) {
            selfDestruction();
            if (isLaserProjectile) {
                gamePlayManager.addPoints(pointsOnDestruction);
            }
        }
    }

    int getPointsOnDestruction() {
        return pointsOnDestruction;
    }


}
