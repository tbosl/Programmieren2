package thd.game.utilities;

import thd.game.level.Difficulty;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
            String content = Files.readString(WICHTEL_GAME_FILE);
            if (content.contains("\n")) {
                StringBuilder sb = new StringBuilder(content);
                sb.replace(0, content.indexOf("\n") - 1, difficulty.toString());
                content = sb.toString();
            } else {
                content = difficulty.toString();
            }
            Files.writeString(WICHTEL_GAME_FILE, content, StandardCharsets.UTF_8);
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
            return content.startsWith(Difficulty.EASY.toString()) ? Difficulty.EASY : Difficulty.STANDARD;
        } catch (IOException e) {
            return Difficulty.STANDARD;
        }
    }

    /**
     * Updates the high-score, if the current score is greater than the score stored in the file.
     */
    public static void updateHighScore(int currentScore) {
        try {
            List<String> content = Files.readAllLines(WICHTEL_GAME_FILE);
            if (content.size() < 2) {
                content.add(String.valueOf(currentScore));
            } else if (Integer.parseInt(content.get(1)) < currentScore) {
                content.set(1, String.valueOf(currentScore));
            }
            Files.write(WICHTEL_GAME_FILE, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static String formatContent(List<String> content) {
        StringBuilder sb = new StringBuilder();
        for (int lineIndex = 0; lineIndex < content.size(); lineIndex++) {
            sb.append(content.get(lineIndex));
            if (lineIndex < content.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
