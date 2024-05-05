package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * Represents the header frame.
 */
public class HeaderFrame extends GameObject {
    private final Color frameColor;
    /**
     * The x coordinate of the beginn of the header box.
     */
    public static final int BOX_BEGINN_X = 300;
    /**
     * The y coordinate of the beginn of the header box.
     */
    public static final int BOX_BEGINN_Y = 1;
    /**
     * The width of the header box.
     */
    public static final int BOX_WIDTH = 680;
    /**
     * The height  of the header box.
     */
    public static final int BOX_HEIGHT = 115;

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
        int xAlignment = BOX_BEGINN_X;
        int yAlignment = BOX_BEGINN_Y;
        int width = BOX_WIDTH;
        int height = BOX_HEIGHT;
        gameView.addRectangleToCanvas(xAlignment, yAlignment, width, height, size, false, frameColor);
    }
}