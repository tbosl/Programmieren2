package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ColorCycleManager;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Bomber;

/**
 * A gameobject used to represent a bomb placed by a bomber.
 */
public class BomberBomb extends GameObject {
    private final ColorCycleManager colorCycleManager;

    /**
     * Creates a bomber bomb with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the bomber bomb.
     * @param bomber          The bomber from which the bomb is dropped.
     */
    public BomberBomb(GameView gameView, GamePlayManager gamePlayManager, Bomber bomber) {
        super(gameView, gamePlayManager);
        colorCycleManager = new ColorCycleManager(gameView, 150);
        position.updateCoordinates(bomber.getPosition());
        size = 20;
        rotation = 0;
    }

    @Override
    public String toString() {
        return "BOMBER BOMB: " + position;
    }

    @Override
    public void addToCanvas() {
        updateRotation();
        gameView.addTextToCanvas("+", position.getX(), position.getY(), size, true, colorCycleManager.findCurrentColor(), rotation);
    }

    private void updateRotation() {
        if (gameView.timer(250, this)) {
            rotation = rotation != 0 ? 0 : 45;
        }
    }
}
