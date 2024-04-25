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

    private final int liveIndex;

    /**
     * Creates a remaining life with a reference of the gameview.
     *
     * @param gameView        The GameView.
     * @param gamePlayManager The manager which is responsible for the occurrence of the bomber bomb.
     * @param liveIndex       The index of the live which will be created.
     */
    public RemainingLive(GameView gameView, GamePlayManager gamePlayManager, int liveIndex) {
        super(gameView, gamePlayManager);
        size = 0.05;
        this.liveIndex = liveIndex;
        position.updateCoordinates(calculateXCoordinate(), 30);
        if (gamePlayManager.getLives() > MAXIMUM_AMOUNT_OF_LIVES) {
            throw new TooManyRemainingLivesException(String.format("The maximum number of lives (%d) has been exceeded (%d).", MAXIMUM_AMOUNT_OF_LIVES, gamePlayManager.getLives()));
        }
    }

    private double calculateXCoordinate() {
        int startCoordinate = 50;
        int offsetPerSmartBomb = 50;
        return startCoordinate + offsetPerSmartBomb * liveIndex;
    }

    @Override
    public String toString() {
        return "REMAINING LIVE: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("spaceship_right.png", position.getX(), position.getY(), size, rotation);
    }

    /**
     * Get the index of the live.
     *
     * @return The index of the live.
     */
    public int getLiveIndex() {
        return liveIndex;
    }
}
