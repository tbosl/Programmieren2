package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Represents a smart bomb.
 */
public class SmartBomb extends GameObject {
    private final int smartBombIndex;

    /**
     * Creates a smart bomb with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the smart bomb.
     */
    public SmartBomb(GameView gameView, GamePlayManager gamePlayManager) {
        this(gameView, gamePlayManager, gamePlayManager.getSmartBombs() - 1);
    }

    /**
     * Creates a smart bomb with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the smart bomb.
     * @param smartBombIndex  The index of this smart bomb.
     */
    public SmartBomb(GameView gameView, GamePlayManager gamePlayManager, int smartBombIndex) {
        super(gameView, gamePlayManager);
        this.smartBombIndex = smartBombIndex;
        position.updateCoordinates(245, calculateYCoordinate());
        size = 0.05;
        distanceToBackground = 0;
    }

    private int calculateYCoordinate() {
        int startCoordinate = 55;
        int offsetPerSmartBomb = 20;
        return startCoordinate + offsetPerSmartBomb * (smartBombIndex);
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
