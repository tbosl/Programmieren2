package thd.gameobjects.movable.astronaut.util;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Astronaut;
import thd.gameobjects.movable.LaserProjectile;
import thd.gameobjects.movable.Spaceship;

/**
 * Manages the collision of the astronaut.
 */
public class AstronautCollisionManager {
    private final GameView gameView;
    private final GamePlayManager gamePlayManager;
    private final Astronaut astronaut;
    private final Position position;
    private final AstronautStateManager astronautStateManager;

    /**
     * Creates a new AstronautCollisionManager.
     *
     * @param gameView              The game view.
     * @param gamePlayManager       The gameplay manager.
     * @param astronaut             The astronaut to manage the collision of.
     * @param astronautStateManager The state manager of the managed astronaut.
     */
    public AstronautCollisionManager(GameView gameView, GamePlayManager gamePlayManager, Astronaut astronaut, AstronautStateManager astronautStateManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        this.astronaut = astronaut;
        this.position = astronaut.getPosition();
        this.astronautStateManager = astronautStateManager;
    }

    /**
     * Reacts to a collision with another CollidingGameObject.
     *
     * @param other The other CollidingGameObject.
     */
    public void reactToCollisionWith(CollidingGameObject other) {
        handleLaserCollision(other);
        handleSpaceshipCollision(other);
    }

    private void handleLaserCollision(CollidingGameObject other) {
        if (other instanceof LaserProjectile) {
            astronaut.selfDestruction();
            if (astronaut.lander != null) {
                astronaut.lander.detachAstronautIfHeGotDestroyed();
            }
        }
    }

    private void handleSpaceshipCollision(CollidingGameObject other) {
        if (other instanceof Spaceship spaceship && !astronaut.isAttachedToLander()) {
            if (position.getY() < MovementPattern.LOWER_BOUNDARY) {
                spaceship.attachedAstronaut = astronaut;
                gamePlayManager.addPoints(Astronaut.SCORE_POINTS_FOR_RESCUE_AND_PICK_UP);
                astronautStateManager.updateStateToFollowSpaceship();
                gameView.playSound("pick_astronaut.wav", false);
            }
        }
    }
}
