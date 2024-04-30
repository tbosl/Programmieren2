package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

class GameWorldManager extends GamePlayManager {
    private String world;
    private int worldOffsetColumns;
    private int worldOffsetLines;

    GameWorldManager(GameView gameView) {
        super(gameView);
        world = """
                B                                                                       \s
                                                                                        \s
                S    S     S     S    S       S   S        S           S       S       S\s
                  S    S      S         S       S      S          S        S        S   \s
                S   S     S          S        S       S      S           S      S       \s
                  S     S  S   S    S       S      S      S       S            S       S\s
                                                                                        \s
                """;
        score = new Score(gameView, this);
        spaceship = new Spaceship(gameView, this);
        astronaut = new Astronaut(gameView, this);
        lander = new Lander(gameView, this, spaceship);
        bomber = new Bomber(gameView, this);
        pod = new Pod(gameView, this, spaceship);
        baiter = new Baiter(gameView, this, spaceship);
        spawnGameObjects();
        spawnGameObjectsFromWorldString();
    }

    private void spawnGameObjects() {
        spawnGameObject(astronaut);
        spawnGameObject(lander);
        spawnGameObject(score);
        spawnGameObject(spaceship);
        spawnGameObject(new HeaderFrame(gameView, this));
        spawnGameObject(new ScannerFrame(gameView, this));
        spawnRemainingLives();
        spawnSmartBombs();
        spawnGameObject(bomber);
        spawnGameObject(pod);
        spawnGameObject(baiter);
    }

    private void spawnRemainingLives() {
        for (int spawnedRemainingLives = 0; spawnedRemainingLives < lives; spawnedRemainingLives++) {
            lifeGained(spawnedRemainingLives);
        }
    }

    private void spawnSmartBombs() {
        for (int spawnedSmartBombs = 0; spawnedSmartBombs < SmartBomb.AMOUNT_OF_SMART_BOMBS_AT_START; spawnedSmartBombs++) {
            smartBombGained();
        }
    }

    private void spawnGameObjectsFromWorldString() {
        String[] lines = world.split("\\R");
        int scale = 100;
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            String line = lines[lineIndex];
            for (int columnIndex = 0; columnIndex < line.length(); columnIndex++) {
                spawnGameObjectsByWorldStringPostion(line, columnIndex, lineIndex, scale);
            }
        }
    }

    private void spawnGameObjectsByWorldStringPostion(String line, int columnIndex, int lineIndex, int scale) {
        char currentChar = line.charAt(columnIndex);
        if (currentChar == ' ') {
            return;
        }
        Position spawnLocation = calculateSpawnLocation(lineIndex, columnIndex, scale);
        if (currentChar == 'S') {
            spawnGameObject(new Star(gameView, this, spawnLocation));
        } else if (currentChar == 'B') {
            spawnGameObject(new Mountains(gameView, this, spawnLocation));
        }
    }

    private Position calculateSpawnLocation(int line, int column, int scale) {
        return new Position((column - worldOffsetColumns) * scale, (line - worldOffsetLines) * scale);
    }
}
