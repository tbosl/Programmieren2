package thd.gameobjects.unmovable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Represents a reserve of the smart bomb.
 */
public class SmartBombReserve extends GameObject {
    /**
     * Creates a smart bomb reserve with a reference of the gameview.
     *
     * @param gameView The GameView
     */
    public SmartBombReserve(GameView gameView) {
        super(gameView);
        position.updateCoordinates(245, 95);
        size = 0.05;
        rotation = 0;
    }

    @Override
    public String toString() {
        return "SMART BOMB RESERVE: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("smart_bomb.png", position.getX(), position.getY(), size, rotation);
    }
}
