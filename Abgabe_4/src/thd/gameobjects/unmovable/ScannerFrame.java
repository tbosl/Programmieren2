package thd.gameobjects.unmovable;

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
     * @param gameView The GameView
     */
    public ScannerFrame(GameView gameView) {
        super(gameView);
        position.updateCoordinates(610, 0);
        blockImage = ScannerBlockImage.SCANNER_FRAME;
        rotation = 0;
        size = 5;
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
