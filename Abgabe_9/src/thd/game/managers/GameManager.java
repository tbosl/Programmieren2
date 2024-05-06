package thd.game.managers;

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
            initializeGame();
        } else if (endOfLevel()) {
            switchToNextLevel();
            initializeLevel();
        }
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
        return lives == 0 || (!hasNextLevel() && endOfLevel());
    }

    private boolean endOfLevel() {
        return provideAllActiveEnemies().isEmpty();
    }
}
