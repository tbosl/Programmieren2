package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Represents the mountains in the background.
 */
public class Mountains extends GameObject implements ShiftableGameObject {
    /**
     * Creates the mountains in the background with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the background mountains.
     * @param spawnLocation   The location where the mountains will be spawned.
     */
    public Mountains(GameView gameView, GamePlayManager gamePlayManager, Position spawnLocation) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(spawnLocation);
        size = 1;
        distanceToBackground = 0;
    }

    @Override
    public String toString() {
        return "MOUNTAINS: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("background.png", position.getX(), position.getY(), size, rotation);
    }
}
