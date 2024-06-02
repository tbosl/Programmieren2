package thd.gameobjects.movable.spaceship.util;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Astronaut;
import thd.gameobjects.movable.Spaceship;

/**
 * Manages the movement of the spaceship.
 */
public class SpaceshipMovementManager {
    private final Spaceship spaceship;
    private static final int LEFT_SCROLL_THRESHOLD = 340;
    private static final int RIGHT_SCROLL_THRESHOLD = 940;
    private final GamePlayManager gamePlayManager;

    /**
     * Creates a new manager for the spaceship movement.
     *
     * @param gamePlayManager The gameplay manager.
     * @param spaceship       The player's spaceship.
     */
    public SpaceshipMovementManager(GamePlayManager gamePlayManager, Spaceship spaceship) {
        this.gamePlayManager = gamePlayManager;
        this.spaceship = spaceship;
    }

    /**
     * Moves the spaceship to the left at the current speed.
     */
    public void left() {
        boolean movementUndone;
        double speedInPixel = spaceship.getSpeedInPixel();
        Position position = spaceship.getPosition();
        if (position.getX() > LEFT_SCROLL_THRESHOLD || spaceship.getAbsolutePosition().getX() < LEFT_SCROLL_THRESHOLD) {
            position.left(speedInPixel);
            movementUndone = undoMovementIfCollisionWithAstronaut('r', false) || undoMovementIfOutOfGameWorld('r');
        } else {
            gamePlayManager.moveWorldToRight(speedInPixel);
            movementUndone = undoMovementIfCollisionWithAstronaut('l', true);
        }
        if (!movementUndone) {
            Position other = new Position(spaceship.getAbsolutePosition());
            other.left(speedInPixel);
            spaceship.updateAbsolutePosition(other);
        }
    }

    /**
     * Moves the spaceship to the right at the current speed.
     */
    public void right() {
        boolean movementUndone;
        double speedInPixel = spaceship.getSpeedInPixel();
        Position position = spaceship.getPosition();
        if ((position.getX() < RIGHT_SCROLL_THRESHOLD) || spaceship.getAbsolutePosition().getX() > GamePlayManager.ABSOLUTE_WORLD_LENGTH - (GameView.WIDTH - RIGHT_SCROLL_THRESHOLD)) {
            position.right(speedInPixel);
            movementUndone = undoMovementIfCollisionWithAstronaut('l', false) || undoMovementIfOutOfGameWorld('l');
        } else {
            gamePlayManager.moveWorldToLeft(speedInPixel);
            movementUndone = undoMovementIfCollisionWithAstronaut('r', true);
        }
        if (!movementUndone) {
            Position other = new Position(spaceship.getAbsolutePosition());
            other.right(speedInPixel);
            spaceship.updateAbsolutePosition(other);
        }
    }

    /**
     * Moves the spaceship to the top at the current speed.
     */
    public void up() {
        double speedInPixel = spaceship.getSpeedInPixel();
        Position position = spaceship.getPosition();
        Position absolutePosition = spaceship.getAbsolutePosition();
        position.up(speedInPixel);
        boolean collisionDetected = undoMovementIfCollisionWithAstronaut('d', false);
        if (!collisionDetected && position.getY() < MovementPattern.UPPER_BOUNDARY) {
            position.updateCoordinates(position.getX(), MovementPattern.UPPER_BOUNDARY);
        }
        absolutePosition.updateCoordinates(absolutePosition.getX(), position.getY());
    }

    /**
     * Moves the spaceship to the bottom at the current speed.
     */
    public void down() {
        double speedInPixel = spaceship.getSpeedInPixel();
        Position position = spaceship.getPosition();
        Position absolutePosition = spaceship.getAbsolutePosition();
        position.down(speedInPixel);
        boolean collisionDetected = undoMovementIfCollisionWithAstronaut('u', false);

        if (!collisionDetected && position.getY() > MovementPattern.LOWER_BOUNDARY) {
            position.updateCoordinates(position.getX(), MovementPattern.LOWER_BOUNDARY);
        }
        absolutePosition.updateCoordinates(absolutePosition.getX(), position.getY());
    }

    private boolean undoMovementIfCollisionWithAstronaut(char counterDirection, boolean worldShift) {
        boolean collisionDetected = false;
        for (Astronaut astronaut : gamePlayManager.provideAllAstronauts()) {
            if (spaceship.collidesWith(astronaut) && astronaut.getPosition().getY() == MovementPattern.LOWER_BOUNDARY) {
                correctPosition(counterDirection, worldShift);
                collisionDetected = true;
                astronaut.stopWalking = true;
                break;
            } else {
                astronaut.stopWalking = false;
            }
        }
        return collisionDetected;
    }

    private void correctPosition(char counterDirection, boolean worldShift) {
        Position position = spaceship.getPosition();
        double speedInPixel = spaceship.getSpeedInPixel();
        switch (counterDirection) {
            case 'd':
                position.down(speedInPixel);
                break;
            case 'u':
                position.up(speedInPixel);
                break;
            case 'l':
                if (worldShift) {
                    gamePlayManager.moveWorldToLeft(speedInPixel);
                } else {
                    position.left(speedInPixel);
                }
                break;
            case 'r':
                if (worldShift) {
                    gamePlayManager.moveWorldToRight(speedInPixel);
                } else {
                    position.right(speedInPixel);
                }
                break;
        }
    }

    private boolean undoMovementIfOutOfGameWorld(char counterDirection) {
        Position absolutePosition = spaceship.getAbsolutePosition();
        Position position = spaceship.getPosition();
        double speedInPixel = spaceship.getSpeedInPixel();
        if (counterDirection == 'l') {
            if (absolutePosition.getX() > GamePlayManager.ABSOLUTE_WORLD_LENGTH - spaceship.getWidth()) {
                position.left(speedInPixel);
                return true;
            }
        } else if (counterDirection == 'r') {
            if (position.getX() < 0) {
                position.right(speedInPixel);
                return true;
            }
        }
        return false;
    }
}
