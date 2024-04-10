package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

import java.util.Random;

public class MutantMovementPatterns extends MovementPattern {
    private final Mutant mutant;
    private int shakeCounterX;
    private int shakeCounterY;

    MutantMovementPatterns(Mutant mutant) {
        shakeCounterX = 0;
        shakeCounterY = 0;
        this.mutant = mutant;
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return new Position(referencePositions[0].getX(), 120);
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        return findClosestTargetPosition(referencePositions[0], referencePositions[1]);
    }

    private Position findClosestTargetPosition(Position spaceship, Position mutant) {
        Position[] targets = initializePossibleTargetPositions(spaceship);
        int closestIndex = 0;
        double shortestDistance = -1;
        for (int index = 0; index < targets.length; index++) {
            double distance = targets[index].distance(mutant);
            if (shortestDistance < 0 || distance < shortestDistance) {
                closestIndex = index;
                shortestDistance = distance;
            }
        }
        return targets[closestIndex];
    }

    private Position[] initializePossibleTargetPositions(Position spaceship) {
        int verticalCenterAlign = 0;
        int horizontalCenterAlign = 10;
        return new Position[]{
                new Position(spaceship.getX() + horizontalCenterAlign, spaceship.getY() + verticalCenterAlign - 120),
                new Position(spaceship.getX() + horizontalCenterAlign + 150, spaceship.getY() + verticalCenterAlign),
                new Position(spaceship.getX() + horizontalCenterAlign, spaceship.getY() + verticalCenterAlign + 120),
                new Position(spaceship.getX() + horizontalCenterAlign - 130, spaceship.getY() + verticalCenterAlign)
        };
    }

    Position shake(Position... referencePositions) {
        Position spaceship = referencePositions[0];
        Position mutant = referencePositions[1];
        Position shakingOutcome = new Position(mutant);
        addShaking(spaceship, mutant, shakingOutcome, true);
        addShaking(spaceship, mutant, shakingOutcome, false);
        return shakingOutcome;
    }

    private void addShaking(Position spaceshipPosition, Position mutantPosition, Position shakingOutcome, boolean horizontal) {
        double spaceshipAxis = horizontal ? spaceshipPosition.getX() : spaceshipPosition.getY();
        double mutantAxis = horizontal ? mutantPosition.getX() : mutantPosition.getY();
        int shakeCounter = horizontal ? shakeCounterX : shakeCounterY;
        if (shakeCounter == 0 && spaceshipAxis < mutantAxis) {
            shakeCounter = 5;
        }
        if (spaceshipAxis <= mutantAxis + 50 && spaceshipAxis >= mutantAxis - 50) {
            mutant.shaking = true;
            if ((shakeCounter / 5) % 2 == 0) {
                if (horizontal) {
                    shakingOutcome.right(4);
                } else {
                    shakingOutcome.up(4);
                }
            } else {
                if (horizontal) {
                    shakingOutcome.left(4);
                } else {
                    shakingOutcome.down(4);
                }
            }

            if (horizontal) {
                shakeCounterX = shakeCounter + 1;
            } else {
                shakeCounterY = shakeCounter + 1;
            }
        } else {
            mutant.shaking = false;
            if (horizontal) {
                shakeCounterX = 0;
            } else {
                shakeCounterY = 0;
            }
        }
    }
}
