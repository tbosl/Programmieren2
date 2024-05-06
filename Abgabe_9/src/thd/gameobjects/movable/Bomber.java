package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.EnemyGameObject;
import thd.gameobjects.unmovable.BomberBomb;

import java.awt.*;
import java.util.Random;

/**
 * A gameobject that represents the alien invader called Bomber.
 */
public class Bomber extends EnemyGameObject {
    private final BomberMovementPattern movementPattern;
    private int currentBombDroppingIntervallInMilliseconds;
    private final Random random;
    private static final int LOWER_INTERVALL_BOUND = 4000;
    private static final int UPPER_INTERVAL_BOUND = 8000;
    private static final int POINTS_ON_DESTRUCTION = 250;

    /**
     * The level of the enemy.
     */
    public static final int ENEMY_LEVEL = 2;

    private static final int ANIMATION_INTERVALL = 300;

    private enum State {
        ANIMATION_1("bomber_animation_1.png"),
        ANIMATION_2("bomber_animation_2.png"),
        ANIMATION_3("bomber_animation_3.png");
        private final String image;

        State(String image) {
            this.image = image;
        }
    }

    private State currentState;


    /**
     * Creates a Bomber with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the game object.
     */
    public Bomber(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager, POINTS_ON_DESTRUCTION, Color.MAGENTA);
        movementPattern = new BomberMovementPattern();
        position.updateCoordinates(movementPattern.startPosition());
        targetPosition.updateCoordinates(movementPattern.nextTargetPosition(position));
        random = new Random();
        currentBombDroppingIntervallInMilliseconds = generateNewBombDroppingIntervall();
        size = 0.1;
        speedInPixel = 2;
        width = 23;
        height = 23;
        distanceToBackground = 1;
        hitBoxOffsets(0, 0, 0, 0);
        currentState = State.ANIMATION_1;
    }

    @Override
    public String toString() {
        return "BOMBER: " + position;
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(movementPattern.nextTargetPosition(position, gamePlayManager.getSpaceship().getAbsolutePosition()));
        }
        double sinusScale = 0.5;
        position.down(Math.round(Math.sin(sinusScale * Math.toRadians(position.getX()))));
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(currentBombDroppingIntervallInMilliseconds, this)) {
            gamePlayManager.spawnGameObject(new BomberBomb(gameView, gamePlayManager, this));
            currentBombDroppingIntervallInMilliseconds = generateNewBombDroppingIntervall();
        }
        if (gameView.timer(ANIMATION_INTERVALL, this)) {
            switchToNextAnimationState();
        }
    }

    private void switchToNextAnimationState() {
        int nextState = (currentState.ordinal() + 1) % State.values().length;
        currentState = State.values()[nextState];
    }

    private int generateNewBombDroppingIntervall() {
        return random.nextInt(LOWER_INTERVALL_BOUND, UPPER_INTERVAL_BOUND);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(currentState.image, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
    }
}
