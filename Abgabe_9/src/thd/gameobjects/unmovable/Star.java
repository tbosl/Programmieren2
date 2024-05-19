package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.Spaceship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the stars in the background.
 */
public class Star extends GameObject implements ShiftableGameObject, ActivatableGameObject<Spaceship> {

    private final Color color;

    /**
     * Creates a star in the background with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the star.
     */
    public Star(GameView gameView, GamePlayManager gamePlayManager, Color color) {
        super(gameView, gamePlayManager);
        size = 8;
        distanceToBackground = 0;
        this.color = color;
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("*", position.getX(), position.getY(), size, true, color, rotation);
    }

    @Override
    public boolean tryToActivate(Spaceship info) {
        double buffer = 1.10;
        return info.getAbsolutePosition().distance(position) < gameViewDiagonale() * buffer;
    }

    private double gameViewDiagonale() {
        return Math.sqrt(Math.pow(GameView.HEIGHT, 2) + Math.pow(GameView.WIDTH, 2));
    }
}
