package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.LaserProjectile;
import thd.gameobjects.movable.Spaceship;

import java.awt.*;

/**
 * The parent class of all enemies.
 */
public abstract class EnemyGameObject extends ScannedGameObject implements ShiftableGameObject {
    private final int pointsOnDestruction;

    protected EnemyGameObject(GameView gameView, GamePlayManager gamePlayManager, int pointsOnDestruction, Color scanColor) {
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

    /**
     * Get the amount of points to be added to the score.
     *
     * @return Amount of points.
     */
    public int getPointsOnDestruction() {
        return pointsOnDestruction;
    }
}
