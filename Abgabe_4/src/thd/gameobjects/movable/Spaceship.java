package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * A gameobject that represents the spaceship controlled by the player.
 */
public class Spaceship extends GameObject {
    private boolean shotInProgress;

    /**
     * Creates the spaceship with a reference of the gameview.
     *
     * @param gameView The GameView
     */
    public Spaceship(GameView gameView) {
        super(gameView);
        position.updateCoordinates(GameView.WIDTH * 0.3, GameView.HEIGHT * 0.5);
        rotation = 0;
        size = 0.1;
        speedInPixel = 7;
        width = 50;
        height = 50;
        shotInProgress = false;
    }

    /**
     * Moves the spaceship to the left at the current speed.
     */
    public void left() {
        position.left(speedInPixel);
    }

    /**
     * Moves the spaceship to the right at the current speed.
     */
    public void right() {
        position.right(speedInPixel);
    }

    /**
     * Moves the spaceship to the top at the current speed.
     */
    public void up() {
        position.up(speedInPixel);
    }

    /**
     * Moves the spaceship to the bottom at the current speed.
     */
    public void down() {
        position.down(speedInPixel);
    }

    /**
     * Shoots a projectile towards the direction the spaceship is currently facing.
     */
    public void shoot() {
        shotInProgress = true;
    }

    @Override
    public String toString() {
        return "SPACESHIP: " + position;
    }

    @Override
    public void updatePosition() {

    }

    @Override
    public void updateStatus() {
        if (gameView.timer(5000, this)) {
            //size++;
        }
    }

    @Override
    public void addToCanvas() {
        if (shotInProgress) {
            gameView.addTextToCanvas("X", position.getX() + 20, position.getY(), 20, true, Color.WHITE, rotation);
            shotInProgress = false;
        } else {
            gameView.addImageToCanvas("spaceship_right.png", position.getX(), position.getY(), size, rotation);
        }
    }
}
