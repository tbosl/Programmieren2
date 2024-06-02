package thd.gameobjects.movable.astronaut.util;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Astronaut;
import thd.gameobjects.movable.AstronautMovementPatterns;
import thd.gameobjects.movable.Lander;

/**
 * Manages the movement of the astronaut.
 */
public class AstronautMovementManager {
    private final Astronaut astronaut;
    private final Position position;
    private final Position targetPosition;
    private final AstronautMovementPatterns astronautMovementPatterns;

    static final int FALL_SPEED_IN_PIXEL = 1;
    private final GamePlayManager gamePlayManager;
    private final GameView gameView;
    private final AstronautStateManager astronautStateManager;

    /**
     * Creates a new AstronautMovementManager.
     *
     * @param gameView                  The game view.
     * @param gamePlayManager           The gameplay manager.
     * @param astronaut                 The astronaut to manage the movement of.
     * @param astronautMovementPatterns The movement patterns of the managed astronaut.
     * @param astronautStateManager     The state manager of the managed astronaut.
     */
    public AstronautMovementManager(GameView gameView, GamePlayManager gamePlayManager, Astronaut astronaut, AstronautMovementPatterns astronautMovementPatterns, AstronautStateManager astronautStateManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        this.astronaut = astronaut;
        this.astronautStateManager = astronautStateManager;
        this.position = astronaut.getPosition();
        this.targetPosition = astronaut.getTargetPosition();
        this.astronautMovementPatterns = astronautMovementPatterns;
    }

    /**
     * Updates the position of the astronaut.
     */
    public void updatePosition() {
        if ((astronautStateManager.checkIfAstronautIsWalking() && !astronaut.stopWalking) || astronautStateManager.checkIfAstronautIsFalling()) {
            walk(astronautStateManager.provideSpeedOfCurrentState());
        } else if (astronautStateManager.checkIfAstronautFollowsLander() || astronaut.getLander() != null) {
            followLander();
        } else if (astronautStateManager.checkIfAstronautFollowsSpaceship() || gamePlayManager.getSpaceship().attachedAstronaut == astronaut) {
            if (position.getY() < MovementPattern.LOWER_BOUNDARY) {
                followSpaceship();
            } else {
                astronautStateManager.detachFromSpaceship();
                gameView.playSound("thank_you.wav", false);
            }
        }
    }

    private void walk(double speedOfCurrentState) {
        if (position.similarTo(targetPosition) || position.getY() < MovementPattern.LOWER_BOUNDARY) {
            targetPosition.updateCoordinates(astronautMovementPatterns.nextTargetPosition(position));
        }
        position.moveToPosition(targetPosition, speedOfCurrentState);
    }

    private void followLander() {
        int horizontalOffset = 2;
        int verticalOffset = 30;
        Lander lander = astronaut.getLander();
        position.moveToPosition(new Position(lander.getPosition().getX() + horizontalOffset, lander.getPosition().getY() + verticalOffset), lander.getSpeedInPixel());

    }

    private void followSpaceship() {
        int horizontalOffset = 10;
        int verticalOffset = 40;
        Position spaceshipPosition = gamePlayManager.getSpaceship().getPosition();
        position.updateCoordinates(spaceshipPosition.getX() + horizontalOffset, spaceshipPosition.getY() + verticalOffset);
    }
}
