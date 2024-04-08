package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject that represents a laser projectile.
 */
public class LaserProjectile extends GameObject {
    private final LaserProjectileMovementPattern movementPattern;

    /**
     * Creates a laser projectile with a reference of the gameview.
     *
     * @param gameView The GameView
     */
    public LaserProjectile(GameView gameView, Spaceship spaceship) {
        super(gameView);
        movementPattern = new LaserProjectileMovementPattern(gameView);
        position.updateCoordinates(movementPattern.startPosition(spaceship.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(position));
        rotation = 0;
        size = 0.08;
        speedInPixel = 40;
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(movementPattern.nextTargetPosition(position));
        }
        position.moveToPosition(targetPosition, speedInPixel);
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
