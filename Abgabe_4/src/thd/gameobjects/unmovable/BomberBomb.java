package thd.gameobjects.unmovable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.ColorCycleManager;
import thd.gameobjects.base.GameObject;

import java.util.Random;

/**
 * A gameobject used to represent a bomb placed by a bomber.
 */
public class BomberBomb extends GameObject {
    /**
     * Creates a bomber bomb with a reference of the gameview.
     *
     * @param gameView The GameView.
     */
    private final ColorCycleManager colorCycleManager;

    public BomberBomb(GameView gameView) {
        super(gameView);
        colorCycleManager = new ColorCycleManager(gameView, 150);
        Random random = new Random();
        position.updateCoordinates(random.nextInt(50, GameView.WIDTH - 50), random.nextInt(200, GameView.HEIGHT - 200));
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
