package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * Represents the header frame.
 */
public class HeaderFrame extends GameObject {
    /**
     * Creates the header frame with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the header frame.
     */
    public HeaderFrame(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 5;
        rotation = 0;
    }

    @Override
    public String toString() {
        return "HEADER FRAME: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addLineToCanvas(0, 118, gameView.WIDTH, 118, size, new Color(34, 16, 142));
        gameView.addRectangleToCanvas(300, 1, GameView.WIDTH - 600, 115, size, false, new Color(34, 16, 142));
    }
}
