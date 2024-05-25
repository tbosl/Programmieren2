package thd.game.level;

import java.awt.*;

/**
 * The seventh level of the game.
 */
public class Level7 extends Level {
    /**
     * Creates the seventh level.
     */
    public Level7() {
        name = "Wave 7";
        number = 7;
        world = """
                                                                                        \s
                                                                                        \s
                S    S     S     S    S       S   S        S           S       S       S\s
                  S    S      S         S       S      S          S        S        S   \s
                S   S     S          S        S       S      S           S      S       \s
                  S     S  S   S    S       S      S      S       S            S       S\s
                      S       S   S      S     S        S   S        S    S        S    \s
                 S  S     S     S     S     S     S       S    S    S    S     S     S  \s
                """;
        worldOffsetColumns = 0;
        worldOffsetLines = 0;
        amountOfAstronauts = 0;
        enemyLevel = 1;
        amountOfEnemiesAtStart = difficultyEasy() ? 8 : 10;
        starColor = Color.blue;
        amountOfEnemiesToSpawnDuringGame = difficultyEasy() ? 7 : 10;
    }
}
