package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

/**
 * A random movement pattern for the enemies.
 */
public class AlienInvadersRandomMovementPattern extends MovementPattern {

    /**
     * Create a new random postion within in the given boundaries
     *
     * @param upBoundary    The upper boundary.
     * @param rightBoundary The right boundary.
     * @param lowBoundary   The low boundary.
     * @param leftBoundary  The left boundary.
     *
     * @return The newly created position.
     */
    public Position generateRandomPosition(double upBoundary, double rightBoundary, double lowBoundary, double leftBoundary) {
        return new Position(random.nextDouble(leftBoundary, rightBoundary), random.nextDouble(upBoundary, lowBoundary));
    }

    /**
     * Create a new random postion within in the given boundaries.
     * Upper and lower boundaries will be given by {@code LOWER_BOUNDARY} and the {@code UPPER_BOUNDARY}
     *
     * @param leftBoundary  The left boundary.
     * @param rightBoundary The right boundary.
     *
     * @return The newly created position.
     */
    public Position generateRandomPosition(double leftBoundary, double rightBoundary) {
        return generateRandomPosition(UPPER_BOUNDARY, rightBoundary, LOWER_BOUNDARY, leftBoundary);
    }

    protected Position generateRandomPositionWithDistanceToSpaceship(double leftBoundary, double rightBoundary, Position spaceshipPosition, int minimumDistance) {
        Position generatedPosition;
        do {
            generatedPosition = generateRandomPosition(UPPER_BOUNDARY, rightBoundary, LOWER_BOUNDARY, leftBoundary);
        } while (generatedPosition.distance(spaceshipPosition) < minimumDistance);
        return generatedPosition;
    }

    protected Position generatePositionWithRandomY(double xCoordinate, int upperBoundary, int lowerBoundary) {
        return new Position(xCoordinate, random.nextInt(upperBoundary, lowerBoundary));
    }
}