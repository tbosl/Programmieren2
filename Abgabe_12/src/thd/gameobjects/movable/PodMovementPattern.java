package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class PodMovementPattern extends MovementPattern {
    private final int upperBoundary;
    private final int lowerBoundary;
    private final int rightBoundary;
    private final int leftBoundary;
    private static final int INNER_MARGIN_TO_SIDE_BORDERS = 50;

    PodMovementPattern(int upperBoundary, int rightBoundary, int lowerBoundary, int leftBoundary) {
        this.upperBoundary = upperBoundary;
        this.rightBoundary = rightBoundary;
        this.lowerBoundary = lowerBoundary;
        this.leftBoundary = leftBoundary;
    }

    PodMovementPattern() {
        this(UPPER_BOUNDARY, GameView.WIDTH - INNER_MARGIN_TO_SIDE_BORDERS, LOWER_BOUNDARY, INNER_MARGIN_TO_SIDE_BORDERS);
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        Position startPosition;
        int minimumDistance = 250;
        do {
            startPosition = generateNewRandomPosition(UPPER_BOUNDARY, GamePlayManager.ABSOLUTE_WORLD_LENGTH - INNER_MARGIN_TO_SIDE_BORDERS, LOWER_BOUNDARY, INNER_MARGIN_TO_SIDE_BORDERS);
        } while (startPosition.distance(referencePositions[1]) < minimumDistance);
        return startPosition;
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        return generateNewRandomPosition();
    }

    private Position generateNewRandomPosition() {
        return generateNewRandomPosition(upperBoundary, rightBoundary, lowerBoundary, leftBoundary);
    }

    private Position generateNewRandomPosition(int upperBoundary, int rightBoundary, int lowerBoundary, int leftBoundary) {
        return new Position(random.nextInt(leftBoundary, rightBoundary), random.nextInt(upperBoundary, lowerBoundary));
    }

}
