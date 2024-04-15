package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class MutantMovementPatterns extends MovementPattern {
    private final GameView gameView;
    private boolean shakeLeft;
    private boolean shakeUp;
    private final int shakeFrequencyInMs;

    MutantMovementPatterns(GameView gameView) {
        this.gameView = gameView;
        shakeLeft = false;
        shakeUp = false;
        shakeFrequencyInMs = 150;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return new Position(referencePositions[0].getX(), MovementPattern.UPPER_BOUNDARY);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        return findClosestTargetPosition(referencePositions[0], referencePositions[1]);
    }

    private Position findClosestTargetPosition(Position spaceship, Position mutant) {
        Position[] targets = initializePossibleTargetPositions(spaceship);
        int indexOfClosestTarget = findIndexOfClosestTargetPosition(targets, mutant);
        return targets[indexOfClosestTarget];
    }

    private int findIndexOfClosestTargetPosition(Position[] targets, Position mutant) {
        int closestIndex = 0;
        double shortestDistance = -1;
        for (int index = 0; index < targets.length; index++) {
            double distance = targets[index].distance(mutant);
            if (shortestDistance < 0 || distance < shortestDistance) {
                closestIndex = index;
                shortestDistance = distance;
            }
        }
        return closestIndex;
    }

    private Position[] initializePossibleTargetPositions(Position spaceship) {
        int verticalCenterAlign = 100;
        int horizontalCenterAlign = 10;
        int rightHorizontalMargin = 130;
        int leftHorizontalMargin = 110;
        return new Position[]{
                new Position(spaceship.getX() + horizontalCenterAlign, spaceship.getY() - verticalCenterAlign),
                new Position(spaceship.getX() + horizontalCenterAlign + rightHorizontalMargin, spaceship.getY()),
                new Position(spaceship.getX() + horizontalCenterAlign, spaceship.getY() + verticalCenterAlign),
                new Position(spaceship.getX() + horizontalCenterAlign - leftHorizontalMargin, spaceship.getY())};
    }

    Position shake(Position... referencePositions) {
        Position spaceship = referencePositions[0];
        Position mutant = referencePositions[1];
        Position shakingOutcome = new Position(mutant);
        updateShakeDirections();
        addShaking(spaceship, mutant, shakingOutcome);
        return shakingOutcome;
    }

    private void updateShakeDirections() {
        if (gameView.timer(shakeFrequencyInMs, this)) {
            shakeLeft = !shakeLeft;
            shakeUp = !shakeUp;
        }
    }

    private void addShaking(Position spaceshipPosition, Position mutantPosition, Position shakingOutcome) {
        int threshold = 50;
        int shakeSpeed = 5;
        if (Math.abs(mutantPosition.getX() - spaceshipPosition.getX()) < threshold) {
            if (shakeLeft) {
                shakingOutcome.left(shakeSpeed);
            } else {
                shakingOutcome.right(shakeSpeed);
            }
        }
        if (Math.abs(mutantPosition.getY() - spaceshipPosition.getY()) < threshold) {
            if (shakeUp) {
                shakingOutcome.up(shakeSpeed);
            } else {
                shakingOutcome.down(shakeSpeed);
            }
        }
    }
}
