package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject that represents the alien invader called Bomber.
 */
public class Bomber extends GameObject {
    private final BomberMovementPattern movementPattern;

    /**
     * Creates a Bomber with a reference of the gameview.
     *
     * @param gameView The GameView
     */
    public Bomber(GameView gameView) {
        super(gameView);
        movementPattern = new BomberMovementPattern();
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(position));
        rotation = 0;
        size = 0.1;
        speedInPixel = 2;
        width = 50;
        height = 50;
    }

    @Override
    public String toString() {
        return "BOMBER: " + position;
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(movementPattern.nextTargetPosition(position));
        }
        position.down(Math.round(Math.sin(0.5 * Math.toRadians(position.getX()))));
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("bomber_animation_1.png", position.getX(), position.getY(), size, rotation);
    }
}
