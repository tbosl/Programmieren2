package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * Represents the text that shows the name of the level in the upper right corner.
 */
public class LevelName extends GameObject {
    private String name;

    /**
     * Create a new level name.
     *
     * @param gameView        The game view.
     * @param gamePlayManager The gameplay manager.
     * @param name            The name of the level, which will be shown in the gameview.
     */
    public LevelName(GameView gameView, GamePlayManager gamePlayManager, String name) {
        super(gameView, gamePlayManager);
        this.name = name;
        int xAlignment = 100;
        int yAlignment = 50;
        size = 20;
        position.updateCoordinates(GameView.WIDTH - xAlignment, yAlignment);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(name, position.getX(), position.getY(), size, true, Color.YELLOW, rotation);
    }
}
