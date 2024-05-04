package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;

class SwarmerMovementPattern extends AlienInvadersRandomMovementPattern {
    private static final int SPAWN_MARGIN = 150;
    private boolean left;
    private final Position lastSpaceshipPosition;

    SwarmerMovementPattern(Position spaceship) {
        left = spaceship.getX() <= GameView.WIDTH / 2f;
        lastSpaceshipPosition = new Position(spaceship);
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        Position pod = referencePositions[0];
        double leftXBoundary = pod.getX() >= SPAWN_MARGIN ? pod.getX() - SPAWN_MARGIN : 0;
        double rightXBoundary = pod.getX() <= GameView.WIDTH - SPAWN_MARGIN ? pod.getX() + SPAWN_MARGIN : GameView.WIDTH;
        double upYBoundary = pod.getY() >= SPAWN_MARGIN + UPPER_BOUNDARY ? pod.getY() - SPAWN_MARGIN : UPPER_BOUNDARY;
        double lowYBoundary = pod.getY() <= LOWER_BOUNDARY ? pod.getY() + SPAWN_MARGIN : LOWER_BOUNDARY;
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
        validateNewTargetPosition(targetPosition);
        lastSpaceshipPosition.updateCoordinates(spaceship);
        return targetPosition;
    }

    private Position generatePositionAtOtherSide(Position spaceship) {
        int targetXCoordinate = left ? 0 : GameView.WIDTH;
        int verticalVariationInPixel = 300;
        return new Position(targetXCoordinate, generateYCoordinate(spaceship, verticalVariationInPixel));
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
