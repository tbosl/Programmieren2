package thd.game.utilities;

import thd.game.level.Difficulty;

class FileModel {
    Difficulty difficulty;
    int highScore;

    FileModel(String fileContent) {
        highScore = 0;
        difficulty = Difficulty.STANDARD;
        for (String line : fileContent.split("\n")) {
            if (!line.contains(":")) {
                continue;
            }
            if (provideKeyOfPair(line).equals(createDifficultyKey())) {
                difficulty = provideValueOfPair(line).equals(Difficulty.EASY.toString()) ? Difficulty.EASY : Difficulty.STANDARD;
            } else if (provideKeyOfPair(line).equals(FileAccess.HIGH_SCORE_KEY)) {
                highScore = provideIntegerValueOfPair(line);
            }
        }
    }

    String parseFileModelToFileContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(createDifficultyKey()).append(":").append(difficulty).append("\n");
        sb.append(FileAccess.HIGH_SCORE_KEY).append(":").append(highScore).append("\n");
        return sb.toString();
    }

    private String provideKeyOfPair(String pair) {
        return pair.substring(0, pair.indexOf(':')).trim();
    }

    private String provideValueOfPair(String pair) {
        return pair.substring(pair.indexOf(':') + 1).trim();
    }

    private int provideIntegerValueOfPair(String pair) {
        return Integer.parseInt(provideValueOfPair(pair));
    }

    private String createDifficultyKey() {
        String className = Difficulty.class.getName();
        return className.substring(className.lastIndexOf('.') + 1);

    }
}
