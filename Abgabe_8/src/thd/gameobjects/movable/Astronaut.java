package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

import java.awt.*;

/**
 * A gameobject that represents the astronauts that get kidnapped by the alien invaders.
 */
public class Astronaut extends ScannedGameObject implements ShiftableGameObject {
    private final AstronautMovementPatterns astronautMovementPatterns;
    private static final int FALL_SPEED_IN_PIXEL = 1;
    boolean pickedUp;
    Lander lander;
    boolean stopWalking;
    static final int SCORE_POINTS_FOR_RESCUE_AND_PICK_UP = 500;

    /**
     * Creates an Astronaut with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the astronaut.
     * @param spawnLeftHalf   Determine whether the astronaut should spawn on the left half of the map.
     */
    public Astronaut(GameView gameView, GamePlayManager gamePlayManager, boolean spawnLeftHalf) {
        super(gameView, gamePlayManager, Color.GRAY.brighter());
        astronautMovementPatterns = new AstronautMovementPatterns(spawnLeftHalf);
        position.updateCoordinates(astronautMovementPatterns.startPosition());
        targetPosition.updateCoordinates(astronautMovementPatterns.nextTargetPosition(position));
        size = 0.08;
        speedInPixel = 0.25;
        height = 40;
        width = 15;
        pickedUp = false;
        stopWalking = false;
        distanceToBackground = 1;
        int hitBoxOffsetWidth = 5;
        hitBoxOffsets(hitBoxOffsetWidth, 0, 0, 0);
    }

    @Override
    public String toString() {
        return "ASTRONAUT: " + position;
    }

    @Override
    public void updatePosition() {
        if (!pickedUp && !stopWalking) {
            walk();
        } else if (lander != null) {
            followLander();
        } else if (gamePlayManager.getSpaceship().attachedAstronaut == this) {
            if (position.getY() < MovementPattern.LOWER_BOUNDARY) {
                followSpaceship();
            } else {
                detachFromSpaceship();
            }
        }
    }

    private void detachFromSpaceship() {
        position.updateCoordinates(position.getX(), MovementPattern.LOWER_BOUNDARY);
        pickedUp = false;
        gamePlayManager.getSpaceship().attachedAstronaut = null;
        gamePlayManager.addPoints(SCORE_POINTS_FOR_RESCUE_AND_PICK_UP);
    }


    private void walk() {
        if (position.similarTo(targetPosition) || position.getY() < MovementPattern.LOWER_BOUNDARY) {
            targetPosition.updateCoordinates(astronautMovementPatterns.nextTargetPosition(position));
        }
        double speedToBeUsed = position.getY() < MovementPattern.LOWER_BOUNDARY ? FALL_SPEED_IN_PIXEL : speedInPixel;
        position.moveToPosition(targetPosition, speedToBeUsed);
    }

    private void followLander() {
        if (lander != null) {
            int horizontalOffset = 2;
            int verticalOffset = 30;
            position.moveToPosition(new Position(lander.getPosition().getX() + horizontalOffset, lander.getPosition().getY() + verticalOffset), lander.getSpeedInPixel());
        }
    }

    private void followSpaceship() {
        int horizontalOffset = 10;
        int verticalOffset = 40;
        Position spaceshipPosition = gamePlayManager.getSpaceship().getPosition();
        position.updateCoordinates(spaceshipPosition.getX() + horizontalOffset, spaceshipPosition.getY() + verticalOffset);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("astronaut.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof LaserProjectile) {
            selfDestruction();
        }
    }
}
