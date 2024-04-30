package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Represents a smart bomb.
 */
public class SmartBomb extends GameObject {
    /**
     * The amount of smart bombs the player has at the beginning of a new game.
     */
    public static final int AMOUNT_OF_SMART_BOMBS_AT_START = 3;
    private final int smartBombIndex;

    /**
     * Creates a smart bomb with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the smart bomb.
     */
    public SmartBomb(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(245, calculateYCoordinate());
        size = 0.05;
        distanceToBackground = 0;
        smartBombIndex = gamePlayManager.getAmountOfSmartBombs();
    }

    private int calculateYCoordinate() {
        int startCoordinate = 55;
        int offsetPerSmartBomb = 20;
        return startCoordinate + offsetPerSmartBomb * (gamePlayManager.getAmountOfSmartBombs() - 1);
    }

    @Override
    public String toString() {
        return "SMART BOMB: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("smart_bomb.png", position.getX(), position.getY(), size, rotation);
    }

    /**
     * Get the index of the smart bomb.
     *
     * @return The index of the smart bomb.
     */
    public int getSmartBombIndex() {
        return smartBombIndex;
    }
}
