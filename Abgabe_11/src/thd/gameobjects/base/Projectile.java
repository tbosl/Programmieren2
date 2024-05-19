package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;

/**
 * Used as a base class for the laser and enemy projectile.
 */
public abstract class Projectile extends CollidingGameObject implements ShiftableGameObject {

    /**
     * Creates a projectile with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the projectile.
     */
    protected Projectile(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        gameView.playSound("projectile.wav", false);
    }

    @Override
    public void updateStatus() {
        int margin = 50;
        if (position.getX() < -margin || position.getX() > GameView.WIDTH + margin || position.getY() < MovementPattern.UPPER_BOUNDARY || position.getY() > GameView.HEIGHT) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
