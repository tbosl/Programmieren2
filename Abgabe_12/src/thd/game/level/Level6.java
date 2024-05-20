package thd.game.level;

import java.awt.*;

/**
 * The sixth level of the game.
 */
public class Level6 extends Level {
    /**
     * Creates the sixth level.
     */
    public Level6() {
        name = "Wave 6";
        number = 6;
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
        amountOfAstronauts = difficultyEasy() ? 5 : 8;
        enemyLevel = 3;
        amountOfEnemiesAtStart = difficultyEasy() ? 6 : 10;
        starColor = Color.PINK;
        amountOfEnemiesToSpawnDuringGame = 5;
    }
}
