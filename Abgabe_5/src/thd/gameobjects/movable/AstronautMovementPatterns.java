package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

import java.util.Random;

class AstronautMovementPatterns extends MovementPattern {
    private boolean walkingRight;

    AstronautMovementPatterns() {
        walkingRight = true;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        int marginToSideBorders = 100;
        return new Position(new Random().nextDouble(marginToSideBorders, GameView.WIDTH - marginToSideBorders), LOWER_BOUNDARY);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        int distanceToWalkInPixel = 50;
        double targetXCoordinate = walkingRight ? referencePositions[0].getX() + distanceToWalkInPixel : referencePositions[0].getX() - distanceToWalkInPixel;
        walkingRight = !walkingRight;
        return new Position(targetXCoordinate, referencePositions[0].getY());
    }
}
