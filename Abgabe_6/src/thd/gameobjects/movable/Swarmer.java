package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;

/**
 * A gameobject that represents the alien invader called Swarmer.
 */
class Swarmer extends EnemyGameObject {
    private final SwarmerMovementPattern movementPattern;
    private final Spaceship spaceship;
    static final int MINIMUM_SWARMERS_PER_SWARM = 5;
    static final int MAXIMUM_SWARMERS_PER_SWARM = 8;
    private static final int POINTS_ON_DESTRUCTION = 150;


    /**
     * Creates a Swarmer with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the swarmer.
     * @param spaceship       The player spaceship - the target of the mutant.
     *                        (This will later come from GameManager or Astronaut)
     * @param pod             The pod from which the swarmer is spawned.
     */
    Swarmer(GameView gameView, GamePlayManager gamePlayManager, Spaceship spaceship, Pod pod) {
        super(gameView, gamePlayManager, POINTS_ON_DESTRUCTION);
        this.spaceship = spaceship;
        movementPattern = new SwarmerMovementPattern(spaceship.getPosition());
        position.updateCoordinates(movementPattern.startPosition(pod.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position, targetPosition));
        size = 0.05;
        speedInPixel = 8;
        width = 15;
        height = 15;
        int hitBoxOffsetX = 5;
        int hitBoxOffsetY = 5;
        hitBoxOffsets(hitBoxOffsetX, hitBoxOffsetY, 0, 0);
    }

    @Override
    public String toString() {
        return "SWARMER: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position, targetPosition));
        positionBeforeMoving.updateCoordinates(position);
        position.moveToPosition(targetPosition, speedInPixel);
        super.updatePosition();
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("swarmer.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
    }
}
