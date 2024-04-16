package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.managers.TooManyRemainingLivesException;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * A gameobject used to represent the remaining lives of the player.
 */
public class RemainingLive extends GameObject {
    /**
     * The maximum amount of lives the player can have during a game.
     */
    public static final int MAXIMUM_AMOUNT_OF_LIVES = 5;

    /**
     * The amount of lives the player has at the beginning of a new game.
     */
    public static final int AMOUNT_OF_LIVES_START = 3;

    /**
     * Creates a remaining life with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the bomber bomb.
     */
    public RemainingLive(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(calculateXCoordinate(), 30);
        size = 0.05;
        gamePlayManager.amountOfRemainingLives++;
        if (gamePlayManager.amountOfRemainingLives > MAXIMUM_AMOUNT_OF_LIVES) {
            throw new TooManyRemainingLivesException(String.format("The maximum number of lives (%d) has been exceeded (%d).", MAXIMUM_AMOUNT_OF_LIVES, gamePlayManager.amountOfRemainingLives));
        }
    }

    private double calculateXCoordinate() {
        int startCoordinate = 50;
        int offsetPerSmartBomb = 50;
        return startCoordinate + offsetPerSmartBomb * gamePlayManager.amountOfRemainingLives;
    }

    @Override
    public String toString() {
        return "REMAINING LIVE: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("spaceship_right.png", position.getX(), position.getY(), size, rotation);
    }
}
