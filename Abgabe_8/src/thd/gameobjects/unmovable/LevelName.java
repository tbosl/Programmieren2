package thd.gameobjects.unmovable;

import thd.game.level.Level;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

public class LevelName extends GameObject {
    private String name;

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
