package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

import java.awt.*;

/**
 * Represents the stars in the background.
 */
public class Star extends GameObject implements ShiftableGameObject {
    /**
     * Creates a star in the background with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the star.
     * @param spawnLocation   The location where the star will be spawned.
     */
    public Star(GameView gameView, GamePlayManager gamePlayManager, Position spawnLocation) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(spawnLocation);
        size = 8;
        distanceToBackground = 0;
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("*", position.getX(), position.getY(), size, true, Color.YELLOW, rotation);
    }
}
