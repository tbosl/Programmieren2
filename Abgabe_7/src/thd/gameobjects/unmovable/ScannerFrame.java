package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Represents the frame of the scanner.
 */
public class ScannerFrame extends GameObject {
    private final String blockImage;

    /**
     * Creates a scanner frame with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the scanner frame.
     */
    public ScannerFrame(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(610, 0);
        blockImage = ScannerBlockImage.SCANNER_FRAME;
        size = 5;
        distanceToBackground = 1;
    }

    @Override
    public String toString() {
        return "SCANNER FRAME: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(blockImage, position.getX(), position.getY(), size, rotation);
    }
}
