package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class AlienInvadersRandomMovementPattern extends MovementPattern {

    protected Position generateRandomPosition(double upBoundary, double rightBoundary, double lowBoundary, double leftBoundary) {
        return new Position(random.nextDouble(leftBoundary, rightBoundary), random.nextDouble(upBoundary, lowBoundary));
    }

    protected Position generateRandomPosition(double leftBoundary, double rightBoundary) {
        return generateRandomPosition(UPPER_BOUNDARY, rightBoundary, LOWER_BOUNDARY, leftBoundary);
    }

    protected Position generatePositionWithRandomY(double xCoordinate, int upperBoundary, int lowerBoundary) {
        return new Position(xCoordinate, random.nextInt(upperBoundary, lowerBoundary));
    }
}
