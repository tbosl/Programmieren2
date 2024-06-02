package thd.gameobjects.movable.astronaut.util;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Astronaut;

/**
 * Manages the state of the astronaut.
 */
public class AstronautStateManager {
    private final Astronaut astronaut;
    private final Position position;
    private final GamePlayManager gamePlayManager;

    private enum State {
        WALKING(0.25),
        FALLING(AstronautMovementManager.FALL_SPEED_IN_PIXEL),
        FOLLOW_LANDER(0),
        FOLLOW_SPACESHIP(0);
        private double speed;

        State(double speed) {
            this.speed = speed;
        }
    }

    private State currentState;

    /**
     * Creates a new AstronautStateManager.
     *
     * @param astronaut       The astronaut to manage the state of.
     * @param gamePlayManager The gameplay manager.
     */
    public AstronautStateManager(Astronaut astronaut, GamePlayManager gamePlayManager) {
        this.astronaut = astronaut;
        this.position = astronaut.getPosition();
        this.gamePlayManager = gamePlayManager;
    }

    /**
     * Resets the state of the astronaut to falling if the followed lander does no longer exist.
     */
    public void resetToFallingIfFollowedLanderDoesNoLongerExist() {
        if (currentState == State.FOLLOW_LANDER && astronaut.getLander() == null) {
            currentState = State.FALLING;
        }
    }

    /**
     * Checks if the astronaut has landed and starts walking if he has.
     */
    public void checkForLandingAndStartWalking() {
        if (currentState == State.FALLING && position.getY() >= MovementPattern.LOWER_BOUNDARY) {
            currentState = State.WALKING;
        }
    }

    /**
     * Detaches the astronaut from the spaceship.
     */
    public void detachFromSpaceship() {
        position.updateCoordinates(position.getX(), MovementPattern.LOWER_BOUNDARY);
        gamePlayManager.getSpaceship().attachedAstronaut = null;
        gamePlayManager.addPoints(astronaut.SCORE_POINTS_FOR_RESCUE_AND_PICK_UP);
        currentState = State.WALKING;
    }

    /**
     * Sets the current state to walking.
     */
    public void updateStateToWalking() {
        currentState = State.WALKING;
    }

    /**
     * Checks if the current state of the astronaut is set to walking.
     *
     * @return {@code true} if the current state is walking, else {@code false}.
     */
    public boolean checkIfAstronautIsWalking() {
        return currentState == State.WALKING;
    }

    /**
     * Sets the current state to follow the lander.
     */
    public void updateStateToFollowLander() {
        currentState = State.FOLLOW_LANDER;
    }

    /**
     * Checks if the current state of the astronaut is set to follow lander.
     *
     * @return {@code true} if the current state is follow lander, else {@code false}.
     */
    public boolean checkIfAstronautFollowsLander() {
        return currentState == State.FOLLOW_LANDER;
    }

    /**
     * Sets the current state to falling.
     */
    public void updateStateToFalling() {
        currentState = State.FALLING;
    }

    /**
     * Checks if the current state of the astronaut is set falling
     *
     * @return {@code true} if the current state is falling, else {@code false}.
     */
    public boolean checkIfAstronautIsFalling() {
        return currentState == State.FALLING;
    }


    /**
     * Sets the current state to follow the spaceship.
     */
    public void updateStateToFollowSpaceship() {
        currentState = State.FOLLOW_SPACESHIP;
    }

    /**
     * Checks if the current state of the astronaut is set to follow spaceship.
     *
     * @return {@code true} if the current state is follow spaceship, else {@code false}.
     */
    public boolean checkIfAstronautFollowsSpaceship() {
        return currentState == State.FOLLOW_SPACESHIP;
    }

    /**
     * Provides the speed of the current state.
     *
     * @return The speed of the current state.
     */
    public double provideSpeedOfCurrentState() {
        return currentState.speed;
    }

}
