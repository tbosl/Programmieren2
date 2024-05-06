package thd.game.level;

import thd.gameobjects.base.EnemyGameObject;
import thd.gameobjects.movable.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Maps the enemies to their levels.
 */
public class EnemyLevelMapper {
    private final HashMap<Class<? extends EnemyGameObject>, Integer> enemyLevels;

    /**
     * Create the mapping.
     */
    public EnemyLevelMapper() {
        enemyLevels = new HashMap<>();
        enemyLevels.put(Baiter.class, Baiter.ENEMY_LEVEL);
        enemyLevels.put(Bomber.class, Bomber.ENEMY_LEVEL);
        enemyLevels.put(Lander.class, Lander.ENEMY_LEVEL);
        enemyLevels.put(Pod.class, Pod.ENEMY_LEVEL);
    }

    /**
     * Provides the list of all enemies that are allowed during the current level.
     *
     * @param level The current level.
     * @return The classes of all possible enemies.
     */
    public List<Class<? extends EnemyGameObject>> providePossibleEnemyClasses(int level) {
        List<Class<? extends EnemyGameObject>> possibleEnemyClasses = new ArrayList<>();
        for (Class<? extends EnemyGameObject> enemyClass : enemyLevels.keySet()) {
            if (enemyLevels.get(enemyClass) <= level) {
                possibleEnemyClasses.add(enemyClass);
            }
        }
        return possibleEnemyClasses;
    }
}
