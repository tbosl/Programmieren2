package thd.game.level;

import java.awt.*;

/**
 * The first level of the game.
 */
public class Level2 extends Level {
    /**
     * Creates the first level.
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
        amountOfAstronauts = difficulty == Difficulty.EASY ? 9 : 8;
        enemyLevel = 1;
        amountOfEnemiesAtStart = difficulty == Difficulty.EASY ? 8 : 12;
        starColor = Color.white;
        amountOfEnemiesToSpawnDuringGame = 5;
    }
}
