package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

import java.util.Random;

class RandomMovementPattern extends MovementPattern {
    private final Random random = new Random();
    private final int upperBoundary;
    private final int lowerBoundary;
    private final int rightBoundary;
    private final int leftBoundary;

    RandomMovementPattern(int upperBoundary, int rightBoundary, int lowerBoundary, int leftBoundary) {
        this.upperBoundary = upperBoundary;
        this.rightBoundary = rightBoundary;
        this.lowerBoundary = lowerBoundary;
        this.leftBoundary = leftBoundary;
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
