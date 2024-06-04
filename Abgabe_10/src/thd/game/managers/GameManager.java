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
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Game Over");
            }
            if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                startNewGame();
            }
        } else if (endOfLevel()) {
            if (!overlay.isMessageShown()) {
                overlay.showMessage("Great Job!");
            }
            if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                switchToNextLevel();
                initializeLevel();
            }
        }
    }

    /**
     * Start a new game.
     */
    void startNewGame() {
        Level.difficulty = Difficulty.EASY;
        pointsRequiredForNewLife = Level.difficulty == Difficulty.EASY ? POINTS_REQUIRED_FOR_NEW_LIFE_EASY : POINTS_REQUIRED_FOR_NEW_LIFE_STANDARD;
        initializeGame();
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        overlay.showMessage(level.name, 2);
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
