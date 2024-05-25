package thd.game.level;

import java.awt.*;

/**
 * The fifth level of the game.
 */
public class Level5 extends Level {
    /**
     * Creates the fifth level.
     */
    public Level5() {
        name = "Wave 5";
        number = 5;
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
        enemyLevel = 3;
        amountOfEnemiesAtStart = difficultyEasy() ? 10 : 14;
        starColor = Color.blue;
        amountOfEnemiesToSpawnDuringGame = 6;
    }
}
