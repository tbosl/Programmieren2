package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.MovementPattern;

/**
 * A gameobject that represents the first type of alien invader.
 */
public class Lander extends GameObject {
    private final LanderMovementPattern landerMovementPattern;
    private final Astronaut nearestAstronaut;
    private final Spaceship spaceship;


    /**
     * Creates a Lander with a reference of the gameview.
     *
     * @param gameView         The GameView
     * @param gamePlayManager  The manager which is responsible for the occurrence of the lander.
     * @param nearestAstronaut The nearest astronaut - the target of the lander.
     * @param spaceship        The player's spaceship.
     */
    public Lander(GameView gameView, GamePlayManager gamePlayManager, Astronaut nearestAstronaut, Spaceship spaceship) {
        super(gameView, gamePlayManager);
        this.nearestAstronaut = nearestAstronaut;
        this.spaceship = spaceship;
        landerMovementPattern = new LanderMovementPattern();
        position.updateCoordinates(landerMovementPattern.startPosition());
        targetPosition.updateCoordinates(landerMovementPattern.nextTargetPosition(nearestAstronaut.getPosition(), position));
        rotation = 0;
        size = 0.08;
        speedInPixel = 5;
        width = 50;
        height = 50;
    }

    @Override
    public String toString() {
        return "LANDER: " + position;
    }

    @Override
    public void updatePosition() {
        if (targetPosition.getY() != 120) {
            targetPosition.updateCoordinates(landerMovementPattern.nextTargetPosition(nearestAstronaut.getPosition(), position));
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void updateStatus() {
        // TODO Check if astronaut is grabbed
        if (position.getY() <= MovementPattern.UPPER_BOUNDARY) {
            gamePlayManager.destroyGameObject(this);
            gamePlayManager.spawnGameObject(new Mutant(gameView, gamePlayManager, spaceship, this));
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("lander.png", position.getX(), position.getY(), size, rotation);
    }
}
