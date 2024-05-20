package thd.game.level;

import java.awt.*;

/**
 * The fourth level of the game.
 */
public class Level4 extends Level {
    /**
     * Creates the fourth level.
     */
    public Level4() {
        name = "Wave 4";
        number = 4;
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
        amountOfAstronauts = difficultyEasy() ? 5 : 4;
        enemyLevel = 2;
        amountOfEnemiesAtStart = difficultyEasy() ? 5 : 7;
        starColor = Color.CYAN;
        amountOfEnemiesToSpawnDuringGame = 5;
    }
}
