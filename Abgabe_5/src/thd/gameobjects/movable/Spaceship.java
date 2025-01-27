package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.MainCharacter;
import thd.gameobjects.base.MovementPattern;

/**
 * A gameobject that represents the spaceship controlled by the player.
 */
public class Spaceship extends GameObject implements MainCharacter {
    private final int shotDurationInMilliseconds;
    private boolean shotAvailable;

    /**
     * Creates the spaceship with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the spaceship.
     */
    public Spaceship(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        double horizontalPositionFactor = 0.3;
        double verticalPositionFactor = 0.5;
        position.updateCoordinates(GameView.WIDTH * horizontalPositionFactor, GameView.HEIGHT * verticalPositionFactor);
        size = 0.1;
        speedInPixel = 7;
        width = 50;
        height = 50;
        shotDurationInMilliseconds = 300;
        shotAvailable = true;
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
        if (position.getY() < MovementPattern.UPPER_BOUNDARY) {
            position.updateCoordinates(position.getX(), MovementPattern.UPPER_BOUNDARY);
        }
    }

    /**
     * Moves the spaceship to the bottom at the current speed.
     */
    public void down() {
        position.down(speedInPixel);
        if (position.getY() > MovementPattern.LOWER_BOUNDARY) {
            position.updateCoordinates(position.getX(), MovementPattern.LOWER_BOUNDARY);
        }
    }

    /**
     * Shoots a projectile towards the direction the spaceship is currently facing.
     */
    @Override
    public void shoot() {
        updateShotAvailable();
        if (shotAvailable) {
            gamePlayManager.spawnGameObject(new LaserProjectile(gameView, gamePlayManager, position));
            shotAvailable = false;
        }
    }

    private void updateShotAvailable() {
        shotAvailable = shotAvailable || gameView.timer(shotDurationInMilliseconds, this);
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

    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("spaceship_right.png", position.getX(), position.getY(), size, rotation);
    }
}
