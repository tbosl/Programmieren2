package thd.game.managers;

import thd.game.level.*;
import thd.game.utilities.GameView;
import thd.gameobjects.unmovable.RemainingLive;

import java.util.List;

class LevelManager extends GameWorldManager {
    private static final int LIVES = 3;
    static final int SMART_BOMBS = 3;
    private List<Level> levels;

    protected LevelManager(GameView gameView) {
        super(gameView);
        initializeGame();
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        initializeGameObjects();
    }

    private void initializeGameObjects() {
        if (smartBombs < LevelManager.SMART_BOMBS) {
            smartBombGained();
        }
        if (level.number > 1 && lives < RemainingLive.MAXIMUM_AMOUNT_OF_LIVES) {
            lifeGained();
        }
        // Die Methode initializeGameObjects() soll in Zukunft dazu genutzt werden um Spielelemente an ein
        // neues Level anzupassen, z.B.
        // o Anpassungen für das Level am Hintergrund machen.
        // o Die Lebensanzeige aktualisieren.
        // o Den Punktestand aus dem vorherigen Level übernehmen.
        // o Einen Countdown neu starten.
        // ➔ Kopieren Sie diesen Text als Kommentar zur Erinnerung für später in diese Methode
    }

    protected void initializeGame() {
        levels = List.of(new Level1(), new Level2(), new Level3(), new Level4(), new Level5(), new Level6(), new Level7());
        level = levels.get(0);
        lives = LIVES;
        smartBombs = SMART_BOMBS;
        points = 0;
    }

    protected boolean hasNextLevel() {
        return level.number < levels.size();
    }

    protected void switchToNextLevel() {
        try {
            level = levels.get(level.number);
        } catch (IndexOutOfBoundsException e) {
            throw new NoMoreLevelsAvailableException("No next level available!");
        }
    }
}
