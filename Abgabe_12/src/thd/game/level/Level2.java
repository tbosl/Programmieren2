package thd.game.level;

import java.awt.*;

/**
 * The second level of the game.
 */
public class Level2 extends Level {
    /**
     * Creates the second level.
     */
    public Level2() {
        name = "Wave 2";
        number = 2;
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
        amountOfAstronauts = difficultyEasy() ? 9 : 8;
        enemyLevel = 1;
        amountOfEnemiesAtStart = difficultyEasy() ? 8 : 12;
        starColor = Color.white;
        amountOfEnemiesToSpawnDuringGame = 5;
    }
}
