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
    private static final int COLOR_CYCLE_DURATION = 150;
    private static final int ROTATION_INTERVALL = 250;
    private static final int DEFAULT_ROTATION = 0;
    private static final int ANGLED_ROTATION = 45;


    /**
     * Creates a bomber bomb with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the bomber bomb.
     * @param bomber          The bomber from which the bomb is dropped.
     */
    public BomberBomb(GameView gameView, GamePlayManager gamePlayManager, Bomber bomber) {
        super(gameView, gamePlayManager);
        colorCycleManager = new ColorCycleManager(gameView, COLOR_CYCLE_DURATION);
        position.updateCoordinates(bomber.getPosition());
        size = 20;
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
        if (gameView.timer(ROTATION_INTERVALL, this)) {
            rotation = rotation != DEFAULT_ROTATION ? DEFAULT_ROTATION : ANGLED_ROTATION;
        }
    }
}
