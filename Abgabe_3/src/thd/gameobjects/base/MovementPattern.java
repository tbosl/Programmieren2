package thd.gameobjects.base;

import java.util.Random;

/**
 * Represents a movement pattern of the objects in the game.
 */
public class MovementPattern {
    protected int currentIndex;
    protected final Random random;

    protected MovementPattern() {
        random = new Random();
    }

    protected Position startPosition(Position... referencePositions) {
        return new Position();
    }

    protected Position nextTargetPosition(Position... referencePositions) {
        return new Position();
    }
}
