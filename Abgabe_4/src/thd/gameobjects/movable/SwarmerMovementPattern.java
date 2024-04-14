package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.AlienInvadersMovementPattern;
import thd.gameobjects.base.Position;

class SwarmerMovementPattern extends AlienInvadersMovementPattern {
    private final int spawnMargin;
    private boolean left;
    private final Position lastSpaceshipPosition;

    SwarmerMovementPattern(Position spaceship) {
        left = spaceship.getX() <= GameView.WIDTH / 2;
        lastSpaceshipPosition = new Position(spaceship);
        spawnMargin = 50;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        Position pod = referencePositions[0];
        double leftXBoundary = pod.getX() >= spawnMargin ? pod.getX() - spawnMargin : 0;
        double rightXBoundary = pod.getX() <= GameView.WIDTH - spawnMargin ? pod.getX() + spawnMargin : GameView.WIDTH;
        double upYBoundary = pod.getY() >= spawnMargin + UPPER_BOUNDARY ? pod.getY() - spawnMargin : UPPER_BOUNDARY;
        double lowYBoundary = pod.getY() <= LOWER_BOUNDARY ? pod.getY() + spawnMargin : LOWER_BOUNDARY;
        return generateRandomPosition(upYBoundary, rightXBoundary, lowYBoundary, leftXBoundary);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        Position spaceship = referencePositions[0];
        Position currentPosition = referencePositions[1];
        Position targetPosition = referencePositions[2];
        if ((currentPosition.getX() <= 0 || currentPosition.getX() >= GameView.WIDTH) || (targetPosition.getX() > 0 && targetPosition.getX() < GameView.WIDTH)) {
            left = !left;
            lastSpaceshipPosition.updateCoordinates(spaceship);
            return generatePositionAtOtherSide(spaceship);
        }
        targetPosition.up(-(spaceship.getY() - lastSpaceshipPosition.getY()));
        validateNewTargetPosition(targetPosition);
        lastSpaceshipPosition.updateCoordinates(spaceship);
        return targetPosition;
    }

    private Position generatePositionAtOtherSide(Position spaceship) {
        if (left) {
            return new Position(0, generateYCoordinate(spaceship, 200));
        } else {
            return new Position(GameView.WIDTH, generateYCoordinate(spaceship, 200));
        }
    }

    private void validateNewTargetPosition(Position currentTarget) {
        if (currentTarget.getY() > LOWER_BOUNDARY) {
            currentTarget.updateCoordinates(currentTarget.getX(), LOWER_BOUNDARY);
        } else if (currentTarget.getY() < UPPER_BOUNDARY) {
            currentTarget.updateCoordinates(currentTarget.getX(), UPPER_BOUNDARY);
        }
    }

    private double generateYCoordinate(Position spaceship, int verticalVariation) {
        double upBoundary = spaceship.getY() - verticalVariation < UPPER_BOUNDARY ? UPPER_BOUNDARY : spaceship.getY() - verticalVariation;
        double lowBoundary = spaceship.getY() + verticalVariation < LOWER_BOUNDARY ? spaceship.getY() + verticalVariation : LOWER_BOUNDARY;
        return random.nextDouble(upBoundary, lowBoundary);
    }
}
