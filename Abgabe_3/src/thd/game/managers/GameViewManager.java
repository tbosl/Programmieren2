package thd.game.managers;

import thd.game.utilities.GameView;

import java.awt.*;

/**
 * The Manager of the GameView.
 * It manages the initialization ({@link #initialize()}) and the game loop ({@link #gameLoop()}) of the gameview.
 */
public class GameViewManager extends GameView {
    private GameManager gameManager;

    @Override
    public void initialize() {
        gameManager = new GameManager(this);
        setWindowTitle("Defender");
        setStatusText("Tobias Bosl - Java Programmierung SS 2024");
        setWindowIcon("spacecraft_right.png");

    }

    @Override
    public void gameLoop() {
        gameManager.gameLoopUpdate();
    }
}