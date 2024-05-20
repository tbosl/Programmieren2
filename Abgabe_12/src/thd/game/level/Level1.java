package thd.game.level;

import java.awt.*;

/**
 * The first level of the game.
 */
public class Level1 extends Level {
    /**
     * Creates the first level.
     */
    public Level1() {
        name = "Wave 1";
        number = 1;
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
        amountOfAstronauts = difficulty == Difficulty.EASY ? 5 : 4;
        enemyLevel = 1;
        amountOfEnemiesAtStart = difficulty == Difficulty.EASY ? 5 : 7;
        starColor = Color.yellow;
        amountOfEnemiesToSpawnDuringGame = 3;
    }
}
