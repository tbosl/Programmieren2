package thd.screens;

import thd.game.utilities.GameView;

/**
 * Represents the end screen of the game.
 */
public class EndScreen {
    private final GameView gameView;

    /**
     * Creates a new end screen.
     *
     * @param gameView The GameView.
     */
    public EndScreen(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Shows the end screen.
     *
     * @param score The amount of points the user scored throughout the game.
     */
    public void showEndScreen(int score, boolean highScore) {
        String message = String.format("You have scored %d points.", score);
        if (highScore) {
            message += "\nCongratulations! New Highscore!";
        }
        gameView.showEndScreen(message);
    }
}
