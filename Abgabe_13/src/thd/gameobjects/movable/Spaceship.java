package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.spaceship.util.SmartBombManager;
import thd.gameobjects.movable.spaceship.util.SpaceshipMovementManager;
import thd.gameobjects.movable.spaceship.util.SpaceshipStateManager;
import thd.gameobjects.unmovable.BomberBomb;

import java.awt.*;

/**
 * A gameobject that represents the spaceship controlled by the player.
 */
public class Spaceship extends ScannedGameObject implements MainCharacter {
    private final int shotDurationInMilliseconds;
    private boolean shotAvailable;
    private final Position absolutePosition;
    /**
     * The astronaut which is currently attached to the spaceship.
     */
    public Astronaut attachedAstronaut;
    private final SmartBombManager smartBombManager;
    private final SpaceshipMovementManager spaceshipMovementManager;
    private final SpaceshipStateManager spaceshipStateManager;

    /**
     * Creates the spaceship with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the spaceship.
     */
    public Spaceship(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager, Color.white);
        double horizontalPositionFactor = 0.125;
        double verticalPositionFactor = 0.5;
        position.updateCoordinates(GameView.WIDTH * horizontalPositionFactor, GameView.HEIGHT * verticalPositionFactor);
        absolutePosition = new Position(position);
        size = 0.1;
        speedInPixel = 7;
        width = 75;
        height = 16;
        shotDurationInMilliseconds = 300;
        shotAvailable = true;
        distanceToBackground = 1;
        int hitBoxOffsetY = 12;
        hitBoxOffsets(0, hitBoxOffsetY, 0, 0);
        smartBombManager = new SmartBombManager(gameView, gamePlayManager);
        spaceshipMovementManager = new SpaceshipMovementManager(gamePlayManager, this);
        spaceshipStateManager = new SpaceshipStateManager();
    }

    /**
     * Moves the spaceship to the left at the current speed.
     */
    public void left() {
        spaceshipMovementManager.left();
        spaceshipStateManager.acceleratingLeft();
    }

    /**
     * Moves the spaceship to the right at the current speed.
     */
    public void right() {
        spaceshipMovementManager.right();
        spaceshipStateManager.acceleratingRight();
    }

    /**
     * Moves the spaceship to the top at the current speed.
     */
    public void up() {
        spaceshipMovementManager.up();
    }

    /**
     * Moves the spaceship to the bottom at the current speed.
     */
    public void down() {
        spaceshipMovementManager.down();
    }

    /**
     * Shoots a projectile towards the direction the spaceship is currently facing.
     */
    @Override
    public void shoot() {
        updateShotAvailable();
        if (shotAvailable && gamePlayManager.getLives() > 0) {
            gamePlayManager.spawnGameObject(new LaserProjectile(gameView, gamePlayManager, position, spaceshipStateManager.facingRight()));
            shotAvailable = false;
        }
    }


    /**
     * Detonates a smart bomb to kill all enemies, which are currently visible on the screen.
     */
    public void detonateSmartBomb() {
        smartBombManager.detonateSmartBomb();
    }

    private void updateShotAvailable() {
        shotAvailable = shotAvailable || gameView.timer(shotDurationInMilliseconds, this);
    }

    @Override
    public String toString() {
        return "SPACESHIP: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(spaceshipStateManager.currentSpaceshipImage(), position.getX(), position.getY(), size, rotation);
        gameView.addImageToCanvas(spaceshipStateManager.currentExhaustImage(), position.getX() + spaceshipStateManager.currentExhaustOffsetX(), position.getY() + spaceshipStateManager.currentExhaustOffsetY(), spaceshipStateManager.currentExhaustSize(), rotation);
        spaceshipStateManager.resetStatus();
    }


    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof BomberBomb || other instanceof EnemyGameObject || other instanceof EnemyProjectile) {
            gamePlayManager.lifeLost();
            if (!(other instanceof BomberBomb)) {
                gameView.playSound("projectile_hit.wav", false);
            }
        }
    }

    /**
     * Get the absolute position of the spaceship.
     *
     * @return the absolute position.
     */
    public Position getAbsolutePosition() {
        return new Position(absolutePosition);
    }

    /**
     * Update the coordinates of the absolute position of the spaceship.
     *
     * @param other The new position.
     */
    public void updateAbsolutePosition(Position other) {
        absolutePosition.updateCoordinates(other);
    }
}
