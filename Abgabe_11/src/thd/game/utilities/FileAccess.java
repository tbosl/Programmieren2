package thd.game.utilities;

import thd.game.level.Difficulty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Used to read and write the difficulty to / from files.
 */
public class FileAccess {
    private static final Path WICHTEL_GAME_FILE = Path.of(System.getProperty("user.home")).resolve("wichtelgame.txt");

    /**
     * Write a difficulty to the disc.
     *
     * @param difficulty The desired difficulty.
     */
    public static void writeDifficultyToDisc(Difficulty difficulty) {
        try {
            Files.writeString(WICHTEL_GAME_FILE, difficulty.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Read the difficulty from the disc.
     * If the file is not found, the difficulty will be set to standard.
     *
     * @return The difficulty that is stored on the disc.
     */
    public static Difficulty readDifficultyFromDisc() {
        try {
            String content = Files.readString(WICHTEL_GAME_FILE);
            return content.equals(Difficulty.EASY.toString()) ? Difficulty.EASY : Difficulty.STANDARD;
        } catch (IOException e) {
            return Difficulty.STANDARD;
        }

    }
}
