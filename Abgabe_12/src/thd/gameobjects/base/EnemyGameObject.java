package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.AlienInvadersRandomMovementPattern;
import thd.gameobjects.movable.LaserProjectile;
import thd.gameobjects.movable.Spaceship;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The parent class of all enemies.
 */
public abstract class EnemyGameObject extends ScannedGameObject implements ShiftableGameObject {
    private final int pointsOnDestruction;
    private int randomMovementIntervall;
    private final List<String> directions;
    private String currentDirection;
    private final Random random;
    private static final int MINIMUM_RANDOM_MOVEMENT_DURATION = 1000;
    private static final int MAXIMUM_RANDOM_MOVEMENT_DURATION = 3000;
    protected final AlienInvadersRandomMovementPattern randomMovementPattern;
    /**
     * The maximum amount of pixel an enemy may be away from the spaceship before it loses the focus.
     */
    public static final int MAXIMUM_ATTACK_FOCUS_DISTANCE = 2000;

    protected EnemyGameObject(GameView gameView, GamePlayManager gamePlayManager, int pointsOnDestruction, Color scanColor) {
        super(gameView, gamePlayManager, scanColor);
        this.pointsOnDestruction = pointsOnDestruction;
        randomMovementPattern = new AlienInvadersRandomMovementPattern();
        random = new Random();
        randomMovementIntervall = random.nextInt(MINIMUM_RANDOM_MOVEMENT_DURATION, MAXIMUM_RANDOM_MOVEMENT_DURATION);
        directions = new ArrayList<>(List.of("l", "r", "u", "d", "lu", "ld", "ru", "rd"));
        currentDirection = "d";
        updateDirection();
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        boolean isLaserProjectile = other instanceof LaserProjectile;
        if (isLaserProjectile || other instanceof Spaceship) {
            selfDestruction();
            if (isLaserProjectile) {
                gamePlayManager.addPoints(pointsOnDestruction);
            }
        }
    }

    @Override
    public void updatePosition() {
        if (gameView.timer(randomMovementIntervall, this) || endOfWorldReached()) {
            randomMovementIntervall = new Random().nextInt(MINIMUM_RANDOM_MOVEMENT_DURATION, MAXIMUM_RANDOM_MOVEMENT_DURATION);
            updateDirection();
        }
        moveInCurrentDirection();
    }

    private void updateDirection() {
        String nextDirection;
        do {
            nextDirection = directions.get(random.nextInt(directions.size()));
        } while (nextDirection == currentDirection && endOfWorldReached());
        currentDirection = nextDirection;
    }

    private void moveInCurrentDirection() {
        double speedA = currentDirection.length() > 1 ? random.nextDouble(speedInPixel) : speedInPixel;
        double speedB = speedInPixel - speedA;
        double verticalSpeed = currentDirection.length() > 1 ? speedA : speedInPixel;
        double horizontalSpeed = currentDirection.length() > 1 ? speedB : speedInPixel;
        if (currentDirection.contains("l")) {
            position.left(horizontalSpeed);
        } else if (currentDirection.contains("r")) {
            position.right(horizontalSpeed);
        }
        if (currentDirection.contains("u")) {
            position.up(verticalSpeed);
        } else if (currentDirection.contains("d")) {
            position.down(verticalSpeed);
        }
    }

    private boolean endOfWorldReached() {
        Position spaceshipAbsolutePosition = gamePlayManager.getSpaceship().getAbsolutePosition();
        double margin = 50;
        boolean right = position.getX() + spaceshipAbsolutePosition.getX() > GamePlayManager.ABSOLUTE_WORLD_LENGTH - margin;
        boolean left = position.getX() + spaceshipAbsolutePosition.getX() < margin;
        boolean up = position.getY() < MovementPattern.UPPER_BOUNDARY + margin;
        boolean low = position.getY() > MovementPattern.LOWER_BOUNDARY - margin;
        return (right && currentDirection.contains("r")) || (left && currentDirection.contains("l")) || (up && currentDirection.contains("u")) || (low && currentDirection.contains("d"));
    }

    /**
     * Get the amount of points to be added to the score.
     *
     * @return Amount of points.
     */
    public int getPointsOnDestruction() {
        return pointsOnDestruction;
    }
}
