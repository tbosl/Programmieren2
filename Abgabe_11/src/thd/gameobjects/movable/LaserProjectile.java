package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.EnemyGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.Projectile;

/**
 * A gameobject that represents a laser projectile.
 */
public class LaserProjectile extends Projectile {

    /**
     * Creates a laser projectile with a reference of the gameview.
     *
     * @param gameView          The GameView.
     * @param gamePlayManager   The manager which is responsible for the occurrence of the laser projectile.
     * @param spaceshipPosition The position of the spaceship, from which the laser got fired.
     * @param shootingRight     Determines the direction in which the laser will be fired.
     */
    LaserProjectile(GameView gameView, GamePlayManager gamePlayManager, Position spaceshipPosition, boolean shootingRight) {
        super(gameView, gamePlayManager);
        int horizontalAlignment = 60;
        int verticalAlignment = 20;
        position.updateCoordinates(spaceshipPosition.getX() + horizontalAlignment, spaceshipPosition.getY() + verticalAlignment);
        size = 0.08;
        speedInPixel = shootingRight ? 20 : -20;
        height = 3;
        width = 40;
        distanceToBackground = 1;
        hitBoxOffsets(0, -2, 0, 0);
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("laser.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public String toString() {
        return "LASER PROJECTILE: " + position;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof EnemyGameObject || other instanceof Astronaut) {
            gamePlayManager.destroyGameObject(this);
            gameView.playSound("projectile_hit.wav", false);
        }
    }
}
