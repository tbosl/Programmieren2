package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Used as a base class for the laser and enemy projectile.
 */
abstract class Projectile extends CollidingGameObject implements ShiftableGameObject {

    /**
     * Creates a projectile with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the projectile.
     */
    Projectile(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
    }

    @Override
    public void updateStatus() {
        if (position.getX() < 0 || position.getX() > GameView.WIDTH || position.getY() < MovementPattern.UPPER_BOUNDARY || position.getY() > GameView.HEIGHT) {
            gamePlayManager.destroyGameObject(this);
        }
    }

}
