package thd.gameobjects.movable.spaceship.util;

/**
 * Manages the states of the spaceship.
 */
public class SpaceshipStateManager {
    private State currentState;

    /**
     * The different states.
     */
    private enum State {
        LEFT("spaceship_left.png", "default_exhaust_left.png", 78, 10, 0.1),
        RIGHT("spaceship_right.png", "default_exhaust_right.png", -12, 10, 0.1),
        LEFT_ACCELERATING("spaceship_left.png", "accelerated_exhaust_left.png", 78, 10, 0.1),
        RIGHT_ACCELERATING("spaceship_right.png", "accelerated_exhaust_right.png", -33, 10, 0.1);

        private final String spaceshipImage;
        private final String exhaustImage;
        private final int exhaustOffsetX;
        private final int exhaustOffsetY;
        private double exhaustSize;

        State(String spaceshipImage, String exhaustImage, int exhaustOffsetX, int exhaustOffsetY, double exhaustSize) {
            this.spaceshipImage = spaceshipImage;
            this.exhaustImage = exhaustImage;
            this.exhaustOffsetX = exhaustOffsetX;
            this.exhaustOffsetY = exhaustOffsetY;
            this.exhaustSize = exhaustSize;
        }
    }

    /**
     * Creates a new state manager for the spaceship.
     */
    public SpaceshipStateManager() {
        this.currentState = State.RIGHT;
    }

    /**
     * Resets the current state.
     */
    public void resetStatus() {
        if (currentState == State.LEFT_ACCELERATING) {
            currentState = State.LEFT;
        } else if (currentState == State.RIGHT_ACCELERATING) {
            currentState = State.RIGHT;
        }
    }

    /**
     * Checks if the spaceship is facing into the direction right.
     *
     * @return {@code true} if the spaceship is looking rightwards, else false.
     */
    public boolean facingRight() {
        return currentState == State.RIGHT || currentState == State.RIGHT_ACCELERATING;
    }

    /**
     * Sets the current state to {@code State.RIGHT_ACCELERATING}.
     */
    public void acceleratingRight() {
        currentState = State.RIGHT_ACCELERATING;
    }

    /**
     * Sets the current state to {@code State.LEFT_ACCELERATING}.
     */
    public void acceleratingLeft() {
        currentState = State.LEFT_ACCELERATING;
    }

    /**
     * Provides the spaceship image of the current state.
     *
     * @return The spaceship image.
     */
    public String currentSpaceshipImage() {
        return currentState.spaceshipImage;
    }

    /**
     * Provides the exhaust image of the current state.
     *
     * @return The exhaust image.
     */
    public String currentExhaustImage() {
        return currentState.exhaustImage;
    }

    /**
     * Provides the x exhaust offset of the current state.
     *
     * @return The x offset of the exhaust.
     */
    public int currentExhaustOffsetX() {
        return currentState.exhaustOffsetX;
    }

    /**
     * Provides the y exhaust offset of the current state.
     *
     * @return The y offset of the exhaust.
     */
    public int currentExhaustOffsetY() {
        return currentState.exhaustOffsetY;
    }

    /**
     * Provides the exhaust size of the current state.
     *
     * @return The size of the exhaust.
     */
    public double currentExhaustSize() {
        return currentState.exhaustSize;
    }

}
