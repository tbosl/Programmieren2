package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class RandomMovementPattern extends MovementPattern {
    private final int upperBoundary;
    private final int lowerBoundary;
    private final int rightBoundary;
    private final int leftBoundary;

    RandomMovementPattern() {
        upperBoundary = UPPER_BOUNDARY;
        rightBoundary = GameView.WIDTH - 50;
        lowerBoundary = LOWER_BOUNDARY;
        leftBoundary = 50;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return generateNewRandomPosition();
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        return generateNewRandomPosition();
    }

    private Position generateNewRandomPosition() {
        return new Position(random.nextInt(leftBoundary, rightBoundary), random.nextInt(upperBoundary, lowerBoundary));
    }

}
