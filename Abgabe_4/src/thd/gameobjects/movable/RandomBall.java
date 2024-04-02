package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * A gameobject that represents a random ball.
 */
public class RandomBall extends GameObject {
    private final QuadraticMovementPattern quadraticMovementPattern;
    private boolean stop;

    /**
     * Creates a RandomBall with a reference of the gameview.
     *
     * @param gameView The GameView
     */
    public RandomBall(GameView gameView) {
        super(gameView);
        quadraticMovementPattern = new QuadraticMovementPattern();
        position.updateCoordinates(new RandomMovementPattern().startPosition());
        targetPosition.updateCoordinates(quadraticMovementPattern.nextTargetPosition());
        speedInPixel = 4;
        width = 50;
        height = 50;
        stop = false;
    }

    @Override
    public String toString() {
        return "RANDOMBALL: " + position;
    }

    @Override
    public void updatePosition() {
        if (gameView.timer(5000, this)) {
            speedInPixel++;
        }
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(quadraticMovementPattern.nextTargetPosition());
        }
        if (stop) {
            if (gameView.timer(2000, this)) {
                stop = false;
            }
        } else {
            if (gameView.timer(8000, this)) {
                stop = true;
            } else {
                position.moveToPosition(targetPosition, speedInPixel);
            }
        }
    }

    @Override
    public void addToCanvas() {
        if (gameView.gameTimeInMilliseconds() < 5000) {
            gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 0, true, Color.YELLOW);
        } else {
            gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 0, true, Color.RED);
        }
        gameView.addOvalToCanvas(targetPosition.getX(), targetPosition.getY(), width, height, 2, false, Color.WHITE);
    }
}
