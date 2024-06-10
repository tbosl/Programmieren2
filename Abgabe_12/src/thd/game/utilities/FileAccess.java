package thd.game.utilities;

import thd.game.level.Difficulty;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Used to read and write the difficulty to / from files.
 */
public class FileAccess {
    private static final Path WICHTEL_GAME_FILE = Path.of(System.getProperty("user.home")).resolve("wichtelgame.txt");
    static final String HIGH_SCORE_KEY = "highScore";

    /**
     * Write a difficulty to the disc.
     *
     * @param difficulty The desired difficulty.
     */
    public static void writeDifficultyToDisc(Difficulty difficulty) {
        try {
            FileModel fileModel = readModelFromFile();
            fileModel.difficulty = difficulty;
            writeModelToFile(fileModel);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Write the high score to the disc.
     *
     * @param score The score which will be written.
     */
    public static void writeHighScoreToDisc(int score) {
        try {
            FileModel fileModel = readModelFromFile();
            fileModel.highScore = score;
            writeModelToFile(fileModel);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void writeModelToFile(FileModel fileModel) throws IOException {
        Files.writeString(WICHTEL_GAME_FILE, fileModel.parseFileModelToFileContent(), StandardCharsets.UTF_8);
    }

    private static FileModel readModelFromFile(){
        try {
            return new FileModel(Files.readString(WICHTEL_GAME_FILE.toAbsolutePath()));
        } catch (IOException e) {
            return new FileModel("");
        }
    }

    /**
     * Read the difficulty from the disc.
     * If the file is not found, the difficulty will be set to standard.
     *
     * @return The difficulty that is stored on the disc.
     */
    public static Difficulty readDifficultyFromDisc() {
        FileModel fileModel = readModelFromFile();
        return fileModel.difficulty;
    }

    /**
     * Read the high score from the disc.
     * If the file is not found, the high score will be set to 0.
     *
     * @return The high score that is stored on the disc.
     */
    public static int readHighScoreFromDisc() {
        FileModel fileModel = readModelFromFile();
        return fileModel.highScore;
    }
}
