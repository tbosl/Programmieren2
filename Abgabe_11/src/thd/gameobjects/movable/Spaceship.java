package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.unmovable.BomberBomb;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A gameobject that represents the spaceship controlled by the player.
 */
public class Spaceship extends ScannedGameObject implements MainCharacter {
    private final int shotDurationInMilliseconds;
    private boolean shotAvailable;
    private boolean smartBombDetonatable;
    private static final int SMART_BOMB_COOLDOWN_IN_MS = 1000;
    private final Position absolutePosition;
    private static final int LEFT_SCROLL_THRESHOLD = 340;
    private static final int RIGHT_SCROLL_THRESHOLD = 940;
    Astronaut attachedAstronaut;

    enum State {
        LEFT("spaceship_left.png", "default_exhaust_left.png", 78, 10, 0.1),
        RIGHT("spaceship_right.png", "default_exhaust_right.png", -12, 10, 0.1),
        LEFT_ACCELERATING("spaceship_left.png", "accelerated_exhaust_left.png", 78, 10, 0.1),
        RIGHT_ACCELERATING("spaceship_right.png", "accelerated_exhaust_right.png", -33, 10, 0.1);

        private final String spaceshipImage;
        private final String exhaustImage;
        private final int exhaustOffsetX;
        private final int exhaustOffsetY;
        private double exhaustSize;

        State(String spaceshipImage, String exhaustImage, int exhaustOffsetX, int exhaustOffsetY, double exhaustSize) {
            this.spaceshipImage = spaceshipImage;
            this.exhaustImage = exhaustImage;
            this.exhaustOffsetX = exhaustOffsetX;
            this.exhaustOffsetY = exhaustOffsetY;
            this.exhaustSize = exhaustSize;
        }
    }

    private State currentState;

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
        smartBombDetonatable = true;
        distanceToBackground = 1;
        int hitBoxOffsetY = 12;
        hitBoxOffsets(0, hitBoxOffsetY, 0, 0);
        currentState = State.RIGHT;
    }

    /**
     * Moves the spaceship to the left at the current speed.
     */
    public void left() {
        boolean movementUndone;
        if (position.getX() > LEFT_SCROLL_THRESHOLD || absolutePosition.getX() < LEFT_SCROLL_THRESHOLD) {
            position.left(speedInPixel);
            movementUndone = undoMovementIfCollisionWithAstronaut('r', false) || undoMovementIfOutOfGameWorld('r');
        } else {
            gamePlayManager.moveWorldToRight(speedInPixel);
            movementUndone = undoMovementIfCollisionWithAstronaut('l', true);
        }
        if (!movementUndone) {
            absolutePosition.left(speedInPixel);
        }
        currentState = State.LEFT_ACCELERATING;
    }

    /**
     * Moves the spaceship to the right at the current speed.
     */
    public void right() {
        boolean movementUndone;
        if ((position.getX() < RIGHT_SCROLL_THRESHOLD) || absolutePosition.getX() > GamePlayManager.ABSOLUTE_WORLD_LENGTH - (GameView.WIDTH - RIGHT_SCROLL_THRESHOLD)) {
            position.right(speedInPixel);
            movementUndone = undoMovementIfCollisionWithAstronaut('l', false) || undoMovementIfOutOfGameWorld('l');
        } else {
            gamePlayManager.moveWorldToLeft(speedInPixel);
            movementUndone = undoMovementIfCollisionWithAstronaut('r', true);
        }
        if (!movementUndone) {
            absolutePosition.right(speedInPixel);
        }
        currentState = State.RIGHT_ACCELERATING;
    }

    /**
     * Moves the spaceship to the top at the current speed.
     */
    public void up() {
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
            if (collidesWith(astronaut) && astronaut.getPosition().getY() == MovementPattern.LOWER_BOUNDARY) {
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
        if (counterDirection == 'l') {
            if (absolutePosition.getX() > GamePlayManager.ABSOLUTE_WORLD_LENGTH - width) {
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

    /**
     * Shoots a projectile towards the direction the spaceship is currently facing.
     */
    @Override
    public void shoot() {
        updateShotAvailable();
        if (shotAvailable) {
            gamePlayManager.spawnGameObject(new LaserProjectile(gameView, gamePlayManager, position, shootingRight()));
            shotAvailable = false;
        }
    }

    private boolean shootingRight() {
        return currentState == State.RIGHT || currentState == State.RIGHT_ACCELERATING;
    }

    /**
     * Detonates a smart bomb to kill all enemies, which are currently visible on the screen.
     */
    public void detonateSmartBomb() {
        if (gameView.timer(SMART_BOMB_COOLDOWN_IN_MS, this) && gamePlayManager.getSmartBombs() > 0) {
            smartBombDetonatable = true;
        }
        if (smartBombDetonatable) {
            gamePlayManager.detonateSmartBomb();
            gameView.playSound("smart_bomb.wav", false);
            List<EnemyGameObject> killedEnemies = killAllEnemiesOnScreen();
            updateScore(killedEnemies);
            smartBombDetonatable = false;
            gameView.timer(SMART_BOMB_COOLDOWN_IN_MS, this);
        }
    }

    private List<EnemyGameObject> killAllEnemiesOnScreen() {
        var killedEnemies = new ArrayList<EnemyGameObject>();
        List<EnemyGameObject> enemies = gamePlayManager.provideAllActiveEnemies();
        for (EnemyGameObject enemy : enemies) {
            double x = enemy.getPosition().getX();
            double y = enemy.getPosition().getY();
            if (x >= 0 && x <= GameView.WIDTH && y >= 0 && y <= GameView.HEIGHT) {
                if (enemy instanceof Lander lander) {
                    removeLanderConnectionToAstronaut(lander);
                }
                enemy.selfDestruction();
                killedEnemies.add(enemy);
            }
        }
        return killedEnemies;
    }

    private void removeLanderConnectionToAstronaut(Lander lander) {
        if (lander.getGrabbedAstronaut() != null) {
            Astronaut astronaut = lander.getGrabbedAstronaut();
            astronaut.lander = null;
        }
    }

    private void updateScore(List<EnemyGameObject> killedEnemies) {
        for (EnemyGameObject enemy : killedEnemies) {
            gamePlayManager.addPoints(enemy.getPointsOnDestruction());
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
    public void addToCanvas() {
        gameView.addImageToCanvas(currentState.spaceshipImage, position.getX(), position.getY(), size, rotation);
        gameView.addImageToCanvas(currentState.exhaustImage, position.getX() + currentState.exhaustOffsetX, position.getY() + currentState.exhaustOffsetY, currentState.exhaustSize, rotation);
        resetStatus();
    }

    private void resetStatus() {
        if (currentState == State.LEFT_ACCELERATING) {
            currentState = State.LEFT;
        } else if (currentState == State.RIGHT_ACCELERATING) {
            currentState = State.RIGHT;
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof BomberBomb || other instanceof EnemyGameObject || other instanceof EnemyProjectile) {
            gamePlayManager.lifeLost();
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


}