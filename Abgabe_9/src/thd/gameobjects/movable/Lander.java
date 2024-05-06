package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShootingEnemyGameObject;

import java.awt.*;
import java.util.Random;

/**
 * A gameobject that represents the first type of alien invader.
 */
public class Lander extends ShootingEnemyGameObject {
    private final LanderMovementPattern landerMovementPattern;
    private Astronaut grabbedAstronaut;
    private final int waitTimeBeforeAttackingAstronaut;
    private final int spawnTime;
    private boolean attackingAllowed;
    private static final int POINTS_ON_DESTRUCTION = 150;

    /**
     * The level of the enemy.
     */
    public static final int ENEMY_LEVEL = 1;


    /**
     * Creates a Lander with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the lander.
     */
    public Lander(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager, POINTS_ON_DESTRUCTION, Color.YELLOW);
        landerMovementPattern = new LanderMovementPattern();
        position.updateCoordinates(landerMovementPattern.startPosition());
        int outerMarginToSideBorders = 100;
        targetPosition.updateCoordinates(landerMovementPattern.generateRandomPosition(-outerMarginToSideBorders, GameView.WIDTH + outerMarginToSideBorders));
        size = 0.08;
        speedInPixel = 5;
        width = 25;
        height = 40;
        spawnTime = gameView.gameTimeInMilliseconds();
        waitTimeBeforeAttackingAstronaut = new Random().nextInt(2000, 6000);
        attackingAllowed = false;
        distanceToBackground = 1;
        int hitBoxOffsetX = 6;
        hitBoxOffsets(hitBoxOffsetX, 0, 0, 0);
    }

    @Override
    public String toString() {
        return "LANDER: " + position;
    }

    @Override
    public void updatePosition() {
        Position astronautTargetPosition = findNearestAstronautPosition();
        if (attackingAllowed && gamePlayManager.provideAllAstronauts().size() > 0 && !astronautTargetPosition.equals(new Position())) {
            targetPosition.updateCoordinates(landerMovementPattern.nextTargetPosition(findNearestAstronautPosition(), position));
        } else {
            targetPosition.updateCoordinates(spaceship.getPosition());
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    private Position findNearestAstronautPosition() {
        Astronaut nearestAstronaut = null;
        double currentDistance = -1;
        for (Astronaut astronaut : gamePlayManager.provideAllAstronauts()) {
            double distance = position.distance(astronaut.getPosition());
            if ((currentDistance < 0 || currentDistance > distance) && (!astronaut.pickedUp ^ astronaut.lander == this)) {
                nearestAstronaut = astronaut;
                currentDistance = distance;
            }
        }
        return nearestAstronaut != null ? nearestAstronaut.getPosition() : new Position();
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        if (position.getY() <= MovementPattern.UPPER_BOUNDARY && grabbedAstronaut != null && grabbedAstronaut.pickedUp) {
            selfDestruction();
            grabbedAstronaut.selfDestruction();
            gamePlayManager.spawnGameObject(new Mutant(gameView, gamePlayManager, spaceship, this));
        }
        if (!attackingAllowed && gameView.gameTimeInMilliseconds() > waitTimeBeforeAttackingAstronaut + spawnTime) {
            attackingAllowed = true;
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("lander.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
        if (other instanceof LaserProjectile || other instanceof Spaceship) {
            removeConnectionToAstronaut();
        }
        if (other instanceof Astronaut astronaut && !astronaut.pickedUp) {
            pickUpAstronaut(astronaut);
        }
    }

    private void removeConnectionToAstronaut() {
        if (grabbedAstronaut != null) {
            grabbedAstronaut.pickedUp = false;
            grabbedAstronaut.lander = null;
        }
        landerMovementPattern.astronautGrabbed = false;
    }

    private void pickUpAstronaut(Astronaut astronaut) {
        grabbedAstronaut = astronaut;
        grabbedAstronaut.pickedUp = true;
        grabbedAstronaut.lander = this;
        landerMovementPattern.astronautGrabbed = true;
    }

    void detachAstronautIfHeGotDestroyed() {
        grabbedAstronaut = null;
        landerMovementPattern.astronautGrabbed = false;
    }

    Astronaut getGrabbedAstronaut() {
        return grabbedAstronaut;
    }
}
