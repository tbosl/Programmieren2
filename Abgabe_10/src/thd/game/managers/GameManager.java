package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.GameView;

class GameManager extends LevelManager {

    GameManager(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }

    private void gameManagement() {
        if (endOfGame()) {
            startNewGame();
        } else if (endOfLevel()) {
            switchToNextLevel();
            initializeLevel();
        }
    }

    /**
     * Start a new game.
     */
    public void startNewGame() {
        Level.difficulty = Difficulty.EASY;
        initializeGame();
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
    }

    @Override
    protected void initializeGame() {
        super.initializeGame();
        initializeLevel();
    }

    private boolean endOfGame() {
        return lives == 0 || (!hasNextLevel() && endOfLevel()) || provideAllAstronauts().isEmpty();
    }

    private boolean endOfLevel() {
        return provideAllActiveEnemies().isEmpty();
    }
}
