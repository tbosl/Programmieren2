package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

public class Square extends GameObject {
    public Square(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(100, 100);
        size = 30;
        speedInPixel = 5;
    }

    @Override
    public String toString() {
        return "SQUARE: " + position;
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
    }

    @Override
    public void updateStatus() {
        if (position.getX() < 0 || position.getX() > GameView.WIDTH || position.getY() < 0 || position.getY() > GameView.HEIGHT) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.getX(), position.getY(), size, size, 3, false, Color.RED);
    }
}
