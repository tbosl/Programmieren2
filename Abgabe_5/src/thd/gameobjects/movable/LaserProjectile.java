package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

/**
 * A gameobject that represents a laser projectile.
 */
class LaserProjectile extends Projectile {

    /**
     * Creates a laser projectile with a reference of the gameview.
     *
     * @param gameView          The GameView.
     * @param gamePlayManager   The manager which is responsible for the occurrence of the laser projectile.
     * @param spaceshipPosition The position of the spaceship, from which the laser got fired.
     */
    LaserProjectile(GameView gameView, GamePlayManager gamePlayManager, Position spaceshipPosition) {
        super(gameView, gamePlayManager);
        // TODO Adjust alignment when spaceship is facing in the other direction.
        int horizontalAlignment = 60;
        int verticalAlignment = 20;
        position.updateCoordinates(spaceshipPosition.getX() + horizontalAlignment, spaceshipPosition.getY() + verticalAlignment);
        size = 0.08;
        speedInPixel = 20;
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
}
