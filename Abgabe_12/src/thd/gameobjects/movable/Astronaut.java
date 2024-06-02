package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.movable.astronaut.util.AstronautCollisionManager;
import thd.gameobjects.movable.astronaut.util.AstronautMovementManager;
import thd.gameobjects.movable.astronaut.util.AstronautStateManager;

import java.awt.*;

/**
 * A gameobject that represents the astronauts that get kidnapped by the alien invaders.
 */
public class Astronaut extends ScannedGameObject implements ShiftableGameObject, ShiftableTargetPostion {
    private final AstronautMovementPatterns astronautMovementPatterns;
    private final AstronautStateManager astronautStateManager;
    private final AstronautMovementManager astronautMovementManager;
    private final AstronautCollisionManager astronautCollisionManager;
    /**
     * The lander to which the astronaut is attached. {@code null} if the astronaut is not attached to a lander.
     */
    public Lander lander;

    /**
     * The amount of points which will be added to the score after the astronaut got rescued and picked up.
     */
    public static final int SCORE_POINTS_FOR_RESCUE_AND_PICK_UP = 500;

    /**
     * Determines whether the astronaut should stop to walk.
     */
    public boolean stopWalking;


    /**
     * Creates an Astronaut with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the astronaut.
     * @param spawnLeftHalf   Determine whether the astronaut should spawn on the left half of the map.
     */
    public Astronaut(GameView gameView, GamePlayManager gamePlayManager, boolean spawnLeftHalf) {
        super(gameView, gamePlayManager, Color.GRAY.brighter());
        astronautMovementPatterns = new AstronautMovementPatterns(spawnLeftHalf, gamePlayManager);
        position.updateCoordinates(astronautMovementPatterns.startPosition());
        targetPosition.updateCoordinates(astronautMovementPatterns.nextTargetPosition(position));
        this.astronautStateManager = new AstronautStateManager(this, gamePlayManager);
        this.astronautMovementManager = new AstronautMovementManager(gameView, gamePlayManager, this, astronautMovementPatterns, astronautStateManager);
        this.astronautCollisionManager = new AstronautCollisionManager(gameView, gamePlayManager, this, astronautStateManager);
        size = 0.08;
        speedInPixel = 0.25;
        height = 40;
        width = 15;
        distanceToBackground = 1;
        astronautStateManager.updateStateToWalking();
        int hitBoxOffsetWidth = 5;
        hitBoxOffsets(hitBoxOffsetWidth, 0, 0, 0);
    }

    @Override
    public String toString() {
        return "ASTRONAUT: " + position;
    }

    @Override
    public void updatePosition() {
        astronautMovementManager.updatePosition();
    }

    @Override
    public void shiftTargetPosition(double shiftX, double shiftY) {
        targetPosition.right(shiftX);
        targetPosition.down(shiftY);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("astronaut.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        astronautStateManager.resetToFallingIfFollowedLanderDoesNoLongerExist();
        astronautStateManager.checkForLandingAndStartWalking();
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        astronautCollisionManager.reactToCollisionWith(other);
    }

    boolean canBeGrabbed() {
        return !astronautStateManager.checkIfAstronautFollowsLander() && !astronautStateManager.checkIfAstronautFollowsSpaceship();
    }

    /**
     * Checks if the astronaut is currently attached to a lander.
     *
     * @return {@code true} if the astronaut is attached to a lander, else {@code false}.
     */
    public boolean isAttachedToLander() {
        return lander != null && astronautStateManager.checkIfAstronautFollowsLander();
    }

    AstronautStateManager getAstronautStateManager() {
        return astronautStateManager;
    }
}
