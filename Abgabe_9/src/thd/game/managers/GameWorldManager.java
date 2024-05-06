package thd.game.managers;

import thd.game.level.EnemyLevelMapper;
import thd.game.utilities.GameView;
import thd.gameobjects.base.EnemyGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

class GameWorldManager extends GamePlayManager {
    private final List<GameObject> activatableGameObjects;
    private final EnemyLevelMapper enemyLevelMapper;
    private final Random random;

    protected GameWorldManager(GameView gameView) {
        super(gameView);
        activatableGameObjects = new LinkedList<>();
        score = new Score(gameView, this);
        spaceship = new Spaceship(gameView, this);
        enemyLevelMapper = new EnemyLevelMapper();
        random = new Random();
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
        spawnUnmovableGameObjects();
        dynamicallySpawnMovableGameObjectsByLevel();
        spawnGameObject(new Jimmy(gameView, this));
    }

    private void spawnUnmovableGameObjects() {
        spawnGameObject(spaceship);
        spawnGameObject(score);
        spawnGameObject(new LevelName(gameView, this, level.name));
        spawnGameObject(new HeaderFrame(gameView, this));
        spawnGameObject(new ScannerFrame(gameView, this));
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

    private void dynamicallySpawnMovableGameObjectsByLevel() {
        spawnAstronautsByLevel();
        spawnEnemiesByLevel();
    }

    private void spawnAstronautsByLevel() {
        for (int spawnedAstronauts = 0; spawnedAstronauts < level.amountOfAstronauts; spawnedAstronauts++) {
            boolean spawnLeftHalf = spawnedAstronauts % 2 == 0;
            spawnGameObject(new Astronaut(gameView, this, spawnLeftHalf));
        }
    }

    private void spawnEnemiesByLevel() {
        List<Class<? extends EnemyGameObject>> allowedEnemies = enemyLevelMapper.providePossibleEnemyClasses(level.number);
        for (int spawnedEnemies = 0; spawnedEnemies < level.amountOfEnemies; spawnedEnemies++) {
            int enemyClassIndex = provideRandomIndex(allowedEnemies.size());
            Class<? extends EnemyGameObject> enemyClass = allowedEnemies.get(enemyClassIndex);
            spawnEnemy(enemyClass);
        }
    }

    private int provideRandomIndex(int bound) {
        return random.nextInt(bound);
    }


    private void spawnEnemy(Class<? extends EnemyGameObject> enemyClass) {
        try {
            Constructor<? extends EnemyGameObject> constructor = enemyClass.getConstructor(GameView.class, GamePlayManager.class);
            EnemyGameObject enemy = constructor.newInstance(gameView, this);
            spawnGameObject(enemy);
        } catch (NoSuchMethodException
                 | InstantiationException
                 | IllegalAccessException
                 | InvocationTargetException e) {
            throw new InvalidEnemyConstructionException("The construction of type " + enemyClass + " failed.");
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
                    spawnStar(x, y);
                } else if (character == 'B') {
                    spawnMountains(x, y);
                }
            }
        }
    }

    private void spawnStar(double x, double y) {
        Star star = new Star(gameView, this);
        star.getPosition().updateCoordinates(x, y);
        addActivatableGameObject(star);
    }

    private void spawnMountains(double x, double y) {
        Mountains mountains = new Mountains(gameView, this);
        mountains.getPosition().updateCoordinates(x, y);
        spawnGameObject(mountains);
    }

    private void addActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.add(gameObject);
        addToShiftableGameObjectsIfShiftable(gameObject);
    }
}
