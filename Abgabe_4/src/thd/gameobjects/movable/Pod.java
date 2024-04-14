package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject used to represent a Pod.
 */
public class Pod extends GameObject {
    private final RandomMovementPattern randomMovementPattern;

    /**
     * Creates a Pod with a reference of the gameview.
     *
     * @param gameView The GameView.
     */
    public Pod(GameView gameView) {
        super(gameView);
        randomMovementPattern = new RandomMovementPattern();
        position.updateCoordinates(randomMovementPattern.startPosition());
        targetPosition.updateCoordinates(randomMovementPattern.nextTargetPosition());
        size = 0.06;
        speedInPixel = 3;
    }

    @Override
    public String toString() {
        return "POD: " + position;
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(randomMovementPattern.nextTargetPosition());
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("pod_animation_1.png", position.getX(), position.getY(), size, rotation);
    }
}