package thd.gameobjects.movable.spaceship.util;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.EnemyGameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the smart bomb interaction.
 */
public class SmartBombManager {
    private static final int SMART_BOMB_COOLDOWN_IN_MS = 1000;
    private final GameView gameView;
    private final GamePlayManager gamePlayManager;
    private boolean smartBombDetonatable;

    /**
     * Creates a new smart bomb manager.
     *
     * @param gameView        The game view.
     * @param gamePlayManager The gameplay manager.
     */
    public SmartBombManager(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;
        smartBombDetonatable = true;
    }

    /**
     * Detonates a smart bomb to kill all enemies, which are currently visible on the screen.
     */
    public void detonateSmartBomb() {
        if (gameView.timer(SMART_BOMB_COOLDOWN_IN_MS, this) && gamePlayManager.getSmartBombs() > 0 && gamePlayManager.getLives() > 0) {
            smartBombDetonatable = true;
        }
        if (smartBombDetonatable) {
            gamePlayManager.detonateSmartBomb();
            gameView.playSound("smart_bomb.wav", false);
            List<EnemyGameObject> killedEnemies = killAllEnemiesOnScreen();
            updateScore(killedEnemies);
            smartBombDetonatable = false;
            gameView.timer(SMART_BOMB_COOLDOWN_IN_MS, this);
        }
    }

    private List<EnemyGameObject> killAllEnemiesOnScreen() {
        var killedEnemies = new ArrayList<EnemyGameObject>();
        List<EnemyGameObject> enemies = gamePlayManager.provideAllActiveEnemies();
        for (EnemyGameObject enemy : enemies) {
            double x = enemy.getPosition().getX();
            double y = enemy.getPosition().getY();
            if (x >= 0 && x <= GameView.WIDTH && y >= 0 && y <= GameView.HEIGHT) {
                enemy.selfDestruction();
                killedEnemies.add(enemy);
            }
        }
        return killedEnemies;
    }

    private void updateScore(List<EnemyGameObject> killedEnemies) {
        for (EnemyGameObject enemy : killedEnemies) {
            gamePlayManager.addPoints(enemy.getPointsOnDestruction());
        }
    }
}
