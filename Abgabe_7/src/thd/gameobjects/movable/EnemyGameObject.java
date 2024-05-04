package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ScanableGameObject;
import thd.gameobjects.base.ScannedGameObject;
import thd.gameobjects.base.ShiftableGameObject;

import java.awt.*;

/**
 * The parent class of all enemies.
 */
public abstract class EnemyGameObject extends ScannedGameObject implements ShiftableGameObject, ScanableGameObject {
    private final int pointsOnDestruction;
    //final ScannerItem scannerItem;

    EnemyGameObject(GameView gameView, GamePlayManager gamePlayManager, int pointsOnDestruction, Color scanColor) {
        super(gameView, gamePlayManager, scanColor);
        this.pointsOnDestruction = pointsOnDestruction;
//        scannerItem = initializeScannerItem(scanColor);
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

//    void selfDestruction() {
//        gamePlayManager.destroyGameObject(this);
//        gamePlayManager.destroyGameObject(scannerItem);
//    }

    int getPointsOnDestruction() {
        return pointsOnDestruction;
    }

    /**
     * Initialize the Scanner item for the current enemy game object.
     *
     * @param scanColor The color which the scanner item will have.
     * @return The initialized ScannerItem.
     */
    @Override
    public ScannerItem initializeScannerItem(Color scanColor) {
        ScannerItem scannerItem = new ScannerItem(gameView, gamePlayManager, this, scanColor, gamePlayManager.getSpaceship());
        gamePlayManager.spawnGameObject(scannerItem);
        return scannerItem;
    }
}
