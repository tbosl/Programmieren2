package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * A gameobject that represents a follower ball.
 */
public class FollowerBall extends GameObject {
    private final RandomBall followMe;

    /**
     * Creates a RandomBall with a reference of the gameview.
     *
     * @param gameView The GameView
     * @param followMe The RandomBall, that the FollowerBall is supposed to follow
     */
    public FollowerBall(GameView gameView, RandomBall followMe) {
        super(gameView);
        this.followMe = followMe;
        targetPosition.updateCoordinates(new Position(followMe.getPosition()));
        speedInPixel = 3;
        width = 50;
        height = 50;
    }

    @Override
    public String toString() {
        return "RANDOMBALL: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(followMe.getPosition());
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.getX(), position.getY(), width, height, 0, true, Color.GREEN);
    }
}

