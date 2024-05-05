package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class GameWorldManager extends GamePlayManager {
    private final String world;
    private final List<GameObject> activatableGameObjects;
    private final int worldOffsetColumns;
    private final int worldOffsetLines;

    protected GameWorldManager(GameView gameView) {
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
        activatableGameObjects = new LinkedList<>();
        worldOffsetColumns = 0;
        worldOffsetLines = 0;
        score = new Score(gameView, this);
        spaceship = new Spaceship(gameView, this);
        astronaut = new Astronaut(gameView, this);
        lander = new Lander(gameView, this);
        bomber = new Bomber(gameView, this);
        pod = new Pod(gameView, this, spaceship);
        baiter = new Baiter(gameView, this, spaceship);
        spawnGameObjects();
        spawnGameObjectsFromWorldString();
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        activateGameObjects();
    }

    private void activateGameObjects() {
        ListIterator<GameObject> iterator = activatableGameObjects.listIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (gameObject instanceof Star star) {
                if (star.tryToActivate(spaceship)) {
                    spawnGameObject(star);
                    iterator.remove();
                }
            }
        }
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
        for (int line = 0; line < lines.length; line++) {
            double y = (line - worldOffsetColumns) * scale;
            for (int column = 0; column < lines[line].length(); column++) {
                double x = (column - worldOffsetLines) * scale;
                char character = lines[line].charAt(column);
                if (character == ' ') {
                    continue;
                }
                if (character == 'S') {
                    Star star = new Star(gameView, this);
                    star.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(star);
                } else if (character == 'B') {
                    Mountains mountains = new Mountains(gameView, this);
                    mountains.getPosition().updateCoordinates(x, y);
                    spawnGameObject(mountains);
                }
            }
        }
    }

    private void addActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.add(gameObject);
        addToShiftableGameObjectsIfShiftable(gameObject);
    }
}
