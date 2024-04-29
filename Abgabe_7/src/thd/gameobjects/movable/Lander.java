package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MovementPattern;

import java.util.Random;

/**
 * A gameobject that represents the first type of alien invader.
 */
public class Lander extends EnemyGameObject {
    private final LanderMovementPattern landerMovementPattern;
    private Astronaut grabbedAstronaut;
    private final Spaceship spaceship;
    private final int waitTimeBeforeAttackingAstronaut;
    private final int spawnTime;
    private boolean attackingAllowed;
    private static final int POINTS_ON_DESTRUCTION = 150;


    /**
     * Creates a Lander with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the lander.
     * @param spaceship       The player's spaceship.
     */
    public Lander(GameView gameView, GamePlayManager gamePlayManager, Spaceship spaceship) {
        super(gameView, gamePlayManager, POINTS_ON_DESTRUCTION);
        this.spaceship = spaceship;
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
        if (attackingAllowed) {
            targetPosition.updateCoordinates(landerMovementPattern.nextTargetPosition(findNearestAstronaut().getPosition(), position));
        } else {
            targetPosition.updateCoordinates(spaceship.getPosition());
        }
        positionBeforeMoving.updateCoordinates(position);
        position.moveToPosition(targetPosition, speedInPixel);
        super.updatePosition();
    }

    @Override
    public void updateStatus() {
        // TODO Check if astronaut is grabbed
        if (position.getY() <= MovementPattern.UPPER_BOUNDARY && grabbedAstronaut != null && grabbedAstronaut.pickedUp) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.destroyGameObject(grabbedAstronaut);
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
            if (grabbedAstronaut != null) {
                grabbedAstronaut.pickedUp = false;
                grabbedAstronaut.lander = null;
            }
            landerMovementPattern.astronautGrabbed = false;
        }
        if (other instanceof Astronaut astronaut && !astronaut.pickedUp) {
            grabbedAstronaut = astronaut;
            grabbedAstronaut.pickedUp = true;
            grabbedAstronaut.lander = this;
            landerMovementPattern.astronautGrabbed = true;
        }
    }

    private Astronaut findNearestAstronaut() {
        Astronaut nearestAstronaut = new Astronaut(gameView, gamePlayManager);
        double currentDistance = -1;
        for (Astronaut astronaut : gamePlayManager.provideAllAstronauts()) {
            double distance = position.distance(astronaut.getPosition());
            if ((currentDistance < 0 || currentDistance > distance) && !astronaut.pickedUp) {
                nearestAstronaut = astronaut;
                currentDistance = distance;
            }
        }
        return nearestAstronaut;
    }

    Astronaut getGrabbedAstronaut() {
        return grabbedAstronaut;
    }
}
