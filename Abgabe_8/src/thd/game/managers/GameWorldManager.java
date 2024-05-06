package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class GameWorldManager extends GamePlayManager {
    private final List<GameObject> activatableGameObjects;

    protected GameWorldManager(GameView gameView) {
        super(gameView);
        activatableGameObjects = new LinkedList<>();
        score = new Score(gameView, this);
        spaceship = new Spaceship(gameView, this);
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

    protected void initializeLevel() {
        activatableGameObjects.clear();
        destroyAllGameObjects();
        spawnGameObjects();
        spawnGameObjectsFromWorldString();
        clearListsForPathDecisionsInGameObjects();
    }

    private void clearListsForPathDecisionsInGameObjects() {
        return; // Will be removed. No path decision lists are required so far.
    }


    private void spawnGameObjects() {
        spaceship = new Spaceship(gameView, this);
        spawnGameObject(spaceship);
        spawnGameObject(new Astronaut(gameView, this));
        spawnGameObject(score);
        spawnGameObject(new LevelName(gameView, this, level.name));
        spawnGameObject(new HeaderFrame(gameView, this));
        spawnGameObject(new ScannerFrame(gameView, this));
        spawnGameObject(new Lander(gameView, this));
        spawnGameObject(new Bomber(gameView, this));
        spawnGameObject(new Pod(gameView, this));
        spawnGameObject(new Baiter(gameView, this));
        spawnRemainingLives();
        spawnSmartBombs();
    }

    private void spawnRemainingLives() {
        for (int spawnedRemainingLives = 0; spawnedRemainingLives < lives; spawnedRemainingLives++) {
            lifeGained(spawnedRemainingLives);
        }
    }

    private void spawnSmartBombs() {
        for (int spawnedSmartBombs = 0; spawnedSmartBombs < smartBombs; spawnedSmartBombs++) {
            spawnGameObject(new SmartBomb(gameView, this, spawnedSmartBombs));
        }
    }

    private void spawnGameObjectsFromWorldString() {
        String[] lines = level.world.split("\\R");
        int scale = 100;
        for (int line = 0; line < lines.length; line++) {
            double y = (line - level.worldOffsetColumns) * scale;
            for (int column = 0; column < lines[line].length(); column++) {
                double x = (column - level.worldOffsetLines) * scale;
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
