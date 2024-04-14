package thd.gameobjects.base;

import thd.game.utilities.GameView;

import java.util.Random;

/**
 * Represents a movement pattern of the objects in the game.
 */
public class MovementPattern {

    /**
     * The upper boundary of the game screen.
     */
    public static final int UPPER_BOUNDARY = 120;

    /**
     * The lower boundary of the game screen.
     */
    public static final int LOWER_BOUNDARY = GameView.HEIGHT - 50;
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
