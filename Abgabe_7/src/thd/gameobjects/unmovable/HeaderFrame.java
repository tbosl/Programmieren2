package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * Represents the header frame.
 */
public class HeaderFrame extends GameObject{
    private final Color frameColor;

    /**
     * Creates the header frame with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the header frame.
     */
    public HeaderFrame(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 5;
        frameColor = new Color(34, 16, 142);
        distanceToBackground = 0;
    }

    @Override
    public String toString() {
        return "HEADER FRAME: " + position;
    }

    @Override
    public void addToCanvas() {
        int verticalLineAlignment = 118;
        gameView.addLineToCanvas(0, verticalLineAlignment, GameView.WIDTH, verticalLineAlignment, size, frameColor);
        int xAlignment = 300;
        int yAlignment = 1;
        int width = GameView.WIDTH - 600;
        int height = 115;
        gameView.addRectangleToCanvas(xAlignment, yAlignment, width, height, size, false, frameColor);
    }
}