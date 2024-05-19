package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.ScannerItem;
import thd.gameobjects.movable.Spaceship;

import java.awt.*;

/**
 * Makes a game object relevant for the scanner area.
 */
public abstract class ScannedGameObject extends CollidingGameObject {

    private final ScannerItem scannerItem;

    /**
     * Crates a new game object that is relevant for the scanner area.
     *
     * @param gameView        Window to show the game object on.
     * @param gamePlayManager Controls the game play.
     * @param scanColor       The color which the scanner item will have.
     */
    protected ScannedGameObject(GameView gameView, GamePlayManager gamePlayManager, Color scanColor) {
        super(gameView, gamePlayManager);
        Spaceship spaceship = this instanceof Spaceship ship ? ship : gamePlayManager.getSpaceship();
        scannerItem = initializeScannerItem(scanColor, spaceship);
    }

    private ScannerItem initializeScannerItem(Color scanColor, Spaceship spaceship) {
        ScannerItem item = new ScannerItem(gameView, gamePlayManager, this, scanColor, spaceship);
        gamePlayManager.spawnGameObject(item);
        return item;
    }

    /**
     * Destroy the game object and it's scan appearance.
     */
    public void selfDestruction() {
        gamePlayManager.destroyGameObject(this);
        gamePlayManager.destroyGameObject(scannerItem);
    }
}
