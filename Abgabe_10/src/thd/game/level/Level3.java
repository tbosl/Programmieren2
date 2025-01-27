package thd.game.level;

import java.awt.*;

/**
 * The first level of the game.
 */
public class Level3 extends Level {
    /**
     * Creates the first level.
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
        amountOfAstronauts = difficulty == Difficulty.EASY ? 9 : 10;
        enemyLevel = 3;
        amountOfEnemies = difficulty == Difficulty.EASY ? 10 : 14;
        starColor = Color.red;
    }
}
