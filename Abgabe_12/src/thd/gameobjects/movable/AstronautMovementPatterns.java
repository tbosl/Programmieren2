package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

import java.util.Random;

class AstronautMovementPatterns extends MovementPattern {
    private final boolean spawnLeft;
    private boolean walkingRight;
    private static final int DISTANCE_TO_WALK_IN_PIXEL = 50;
    private final GamePlayManager gamePlayManager;

    AstronautMovementPatterns(boolean spawnLeft, GamePlayManager gamePlayManager) {
        walkingRight = true;
        this.spawnLeft = spawnLeft;
        this.gamePlayManager = gamePlayManager;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        int marginToSideBorders = 150;
        int lowerBound = spawnLeft ? marginToSideBorders : GamePlayManager.ABSOLUTE_WORLD_LENGTH / 2;
        int upperBound = spawnLeft ? GamePlayManager.ABSOLUTE_WORLD_LENGTH / 2 : GamePlayManager.ABSOLUTE_WORLD_LENGTH - marginToSideBorders;
        return new Position(new Random().nextDouble(lowerBound, upperBound), LOWER_BOUNDARY);
    }


    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        if (referencePositions[0].getY() < LOWER_BOUNDARY) {
            return new Position(referencePositions[0].getX(), LOWER_BOUNDARY);
        }
        walkingRight = !walkingRight;
        return new Position(createTargetXCoordinate(referencePositions[0]), referencePositions[0].getY());
    }


    private double createTargetXCoordinate(Position position) {
        double targetXCoordinate = walkingRight ? position.getX() + DISTANCE_TO_WALK_IN_PIXEL : position.getX() - DISTANCE_TO_WALK_IN_PIXEL;
        return adjustIfIllegalXCoordinate(targetXCoordinate);
    }

    // TODO Absolute position
    private double adjustIfIllegalXCoordinate(double targetXCoordinate) {
        double leftBorder = leftBorder();
        if (targetXCoordinate < leftBorder) {
            return leftBorder;
        }
        double rightBorder = rightBorder();
        if (targetXCoordinate > rightBorder) {
            return rightBorder;
        }
        return targetXCoordinate;
    }

    private double leftBorder() {
        return -gamePlayManager.getSpaceship().getAbsolutePosition().getX();
    }

    private double rightBorder() {
        int margin = 50;
        return GamePlayManager.ABSOLUTE_WORLD_LENGTH - gamePlayManager.getSpaceship().getAbsolutePosition().getX() - margin;
    }
}
