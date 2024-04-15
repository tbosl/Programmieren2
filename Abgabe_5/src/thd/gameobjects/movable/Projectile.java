package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.MovementPattern;

/**
 * Used as a base class for the laser and enemy projectile.
 */
abstract class Projectile extends GameObject {

    /**
     * Creates a projectile with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the enemy projectile.
     */
    Projectile(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
    }

    @Override
    public void updateStatus() {
        if (position.getX() < 0 || position.getX() > GameView.WIDTH || position.getY() < MovementPattern.UPPER_BOUNDARY || position.getY() > MovementPattern.LOWER_BOUNDARY) {
            gamePlayManager.destroyGameObject(this);
        }
    }

}
