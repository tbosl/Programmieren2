package thd.game.level;

import java.awt.*;

/**
 * The third level of the game.
 */
public class Level3 extends Level {
    /**
     * Creates the third level.
     */
    public Level3() {
        name = "Wave 3";
        number = 3;
        world = """
                B                                                                       \s
                                                                                        \s
                S    S     S     S    S       S   S        S           S       S       S\s
                  S    S      S         S       S      S          S        S        S   \s
                S   S     S          S        S       S      S           S      S       \s
                  S     S  S   S    S       S      S      S       S            S       S\s
                                                                                        \s
                """;
        worldOffsetColumns = 0;
        worldOffsetLines = 0;
        amountOfAstronauts = difficultyEasy() ? 9 : 10;
        enemyLevel = 2;
        amountOfEnemiesAtStart = difficultyEasy() ? 10 : 14;
        starColor = Color.red;
        amountOfEnemiesToSpawnDuringGame = 5;
    }
}
