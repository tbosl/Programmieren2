package thd.game.level;

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
    }
}
