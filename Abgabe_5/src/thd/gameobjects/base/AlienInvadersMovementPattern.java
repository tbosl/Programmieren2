package thd.gameobjects.base;

/**
 * Represents the base movement of the alien invaders.
 */
public class AlienInvadersMovementPattern extends MovementPattern {

    protected Position generateRandomPosition(double upBoundary, double rightBoundary, double lowBoundary, double leftBoundary) {
        return new Position(random.nextDouble(leftBoundary, rightBoundary), random.nextDouble(upBoundary, lowBoundary));
    }

    protected Position generateRandomPosition(double leftBoundary, double rightBoundary) {
        return generateRandomPosition(UPPER_BOUNDARY, rightBoundary, LOWER_BOUNDARY, leftBoundary);
    }
}
