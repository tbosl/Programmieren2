package thd.game.level;

import java.awt.*;

/**
 * The first level of the game.
 */
public class Level6 extends Level {
    /**
     * Creates the first level.
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
        amountOfAstronauts = difficulty == Difficulty.EASY ? 5 : 8;
        enemyLevel = 3;
        amountOfEnemiesAtStart = difficulty == Difficulty.EASY ? 6 : 10;
        starColor = Color.PINK;
        amountOfEnemiesToSpawnDuringGame = 5;
    }
}
