package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.FileAccess;
import thd.game.utilities.GameView;
import thd.screens.EndScreen;
import thd.screens.StartScreen;

class GameManager extends LevelManager {
    private int themeSoundId;

    GameManager(GameView gameView) {
        super(gameView);
        themeSoundId = -1;
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }

    private void gameManagement() {
        if (endOfGame()) {
            if (!overlay.isMessageShown()) {
                if (gameWon()) {
                    overlay.showMessage("Good Job! You saved the astronauts!");
                    gameView.playSound("winner.wav", false);
                } else {
                    overlay.showMessage("Game Over");
                    gameView.playSound("game_over.wav", false);
                }
            }
            if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                EndScreen endScreen = new EndScreen(gameView);
                boolean highScore = points > FileAccess.readHighScoreFromDisc();
                if (highScore) {
                    FileAccess.writeHighScoreToDisc(points);
                }
                endScreen.showEndScreen(points, highScore);
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
        Difficulty difficulty = FileAccess.readDifficultyFromDisc();
        StartScreen startScreen = new StartScreen(gameView);
        startScreen.showStartScreenWithPreselectedDifficulty(difficulty);
        difficulty = startScreen.getSelectedDifficulty();
        FileAccess.writeDifficultyToDisc(difficulty);
        Level.difficulty = difficulty;
        pointsRequiredForNewLife = Level.difficulty == Difficulty.EASY ? POINTS_REQUIRED_FOR_NEW_LIFE_EASY : POINTS_REQUIRED_FOR_NEW_LIFE_STANDARD;
        initializeGame();
        if (themeSoundId == -1) {
            themeSoundId = gameView.playSound("theme.wav", true);
        }
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        overlay.showMessage(level.name, 2);
        gameView.playSound("level_begin.wav", false);
    }

    @Override
    protected void initializeGame() {
        super.initializeGame();
        initializeLevel();
    }

    private boolean endOfGame() {
        return lives == 0 || (!hasNextLevel() && endOfLevel()) || (provideAllAstronauts().isEmpty() && level.amountOfAstronauts > 0);
    }

    private boolean endOfLevel() {
        return provideAllActiveEnemies().isEmpty() && provideAllEnemiesInToBeAdded().isEmpty() && spawnedEnemiesDuringLevel == level.amountOfEnemiesToSpawnDuringGame;
    }

    private boolean gameWon() {
        return lives > 0 && (!hasNextLevel() && endOfLevel()) && (!provideAllAstronauts().isEmpty() || level.amountOfAstronauts == 0);
    }
}
