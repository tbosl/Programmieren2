package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

import java.util.Random;

class AstronautMovementPatterns extends MovementPattern {
    private final boolean spawnLeft;
    private boolean walkingRight;

    AstronautMovementPatterns(boolean spawnLeft) {
        walkingRight = true;
        this.spawnLeft = spawnLeft;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        int marginToSideBorders = 100;
        int lowerBound = spawnLeft ? marginToSideBorders : GamePlayManager.ABSOLUTE_WORLD_LENGTH / 2;
        int upperBound = spawnLeft ? GamePlayManager.ABSOLUTE_WORLD_LENGTH / 2 : GamePlayManager.ABSOLUTE_WORLD_LENGTH - marginToSideBorders;
        return new Position(new Random().nextDouble(lowerBound, upperBound), LOWER_BOUNDARY);
    }


    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        if (referencePositions[0].getY() < LOWER_BOUNDARY) {
            return new Position(referencePositions[0].getX(), LOWER_BOUNDARY);
        }
        int distanceToWalkInPixel = 50;
        double targetXCoordinate = walkingRight ? referencePositions[0].getX() + distanceToWalkInPixel : referencePositions[0].getX() - distanceToWalkInPixel;
        walkingRight = !walkingRight;
        return new Position(targetXCoordinate, referencePositions[0].getY());
    }
}
