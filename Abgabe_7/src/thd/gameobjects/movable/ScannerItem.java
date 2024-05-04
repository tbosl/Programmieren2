package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.unmovable.HeaderFrame;

import java.awt.*;

/**
 * The scanner item represents a game object in the game view.
 */
public class ScannerItem extends GameObject {
    private final GameObject scanableGameObject;
    private final Spaceship spaceship;
    private final Color color;

    /**
     * Create a new Scanner item.
     *
     * @param gameView           The game view.
     * @param gamePlayManager    The gameplay manager.
     * @param scanableGameObject The scanable game object which will be represented by the scanner item.
     * @param color              The color of the scanner item.
     * @param spaceship          The player's spaceship.
     */
    public ScannerItem(GameView gameView, GamePlayManager gamePlayManager, GameObject scanableGameObject, Color color, Spaceship spaceship) {
        super(gameView, gamePlayManager);
        this.scanableGameObject = scanableGameObject;
        this.color = color;
        size = 5;
        distanceToBackground = 1;
        this.spaceship = spaceship;
    }

    @Override
    public void updatePosition() {
        double x = calculateNewXCoordinate();
        double y = calculateNewYCoordinate();
        position.updateCoordinates(x, y);
    }

    private double calculateNewXCoordinate() {
        int frameSize = 5;
        double targetX;
        if (scanableGameObject instanceof Spaceship) {
            targetX = spaceship.getAbsolutePosition().getX();
        } else {
            targetX = scanableGameObject.getPosition().getX() + spaceship.getAbsolutePosition().getX() - spaceship.getPosition().getX();
        }
        double scale = (HeaderFrame.BOX_WIDTH - 2 * frameSize) / (GamePlayManager.ABSOLUTE_WORLD_LENGTH + 0d);
        return HeaderFrame.BOX_BEGINN_X + frameSize + (targetX * scale);
    }


    private double calculateNewYCoordinate() {
        int frameSize = 5;
        double targetY = scanableGameObject.getPosition().getY();
        double scale = (HeaderFrame.BOX_HEIGHT - 2 * frameSize) / (GameView.HEIGHT + 0d);
        return HeaderFrame.BOX_BEGINN_Y + frameSize + (targetY * scale);
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.getX(), position.getY(), size, size, 1, true, color);
    }
}
