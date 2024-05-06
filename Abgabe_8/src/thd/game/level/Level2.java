package thd.game.level;

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
    }
}
