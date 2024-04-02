package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class QuadraticMovementPattern extends MovementPattern {
    private final Position[] pattern;

    QuadraticMovementPattern() {
        pattern = new Position[]{
                new Position(100, 100),
                new Position(1100, 100),
                new Position(1100, 600),
                new Position(100, 600)};
        currentIndex = -1;
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        currentIndex++;
        if (currentIndex >= pattern.length) {
            currentIndex = 0;
        }
        return pattern[currentIndex];
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return nextTargetPosition();
    }
}
