package thd.gameobjects.movable;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject that represents the alien invader called Mutant.
 */
public class Mutant extends GameObject {
    private final MutantMovementPatterns movementPattern;
    private final Spaceship spaceship;


    /**
     * Creates a Mutant with a reference of the gameview.
     *
     * @param gameView    The GameView.
     * @param spaceship   The player spaceship - the target of the mutant.
     *                    (This will later come from GameManager or Astronaut)
     * @param preMutation The lander which is mutated.
     */
    public Mutant(GameView gameView, Spaceship spaceship, Lander preMutation) {
        super(gameView);
        this.spaceship = spaceship;
        movementPattern = new MutantMovementPatterns(gameView);
        position.updateCoordinates(movementPattern.startPosition(preMutation.getPosition()));
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position));
        rotation = 0;
        size = 0.08;
        speedInPixel = 4;
        width = 50;
        height = 50;
    }

    @Override
    public String toString() {
        return "MUTANT: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(spaceship.getPosition(), position));
        position.moveToPosition(movementPattern.shake(spaceship.getPosition(), position), 4);
        if (position.distance(targetPosition) > 30) {
            position.moveToPosition(targetPosition, speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("mutant.png", position.getX(), position.getY(), size, rotation);
    }
}

