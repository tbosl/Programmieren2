package thd.game.bin;

import thd.game.managers.GameViewManager;

/**
 * The main class of the game.
 */
public class StartGame {
    /**
     * Start a new game session.
     *
     * @param args Arguments from the terminal
     */
    public static void main(String[] args) {
        GameViewManager gameViewManager = new GameViewManager();
        gameViewManager.startGame();
    }
}
