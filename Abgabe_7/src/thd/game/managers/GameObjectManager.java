package thd.game.managers;

import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Astronaut;
import thd.gameobjects.movable.EnemyGameObject;
import thd.gameobjects.unmovable.RemainingLive;
import thd.gameobjects.unmovable.SmartBomb;

import java.util.LinkedList;
import java.util.List;

class GameObjectManager extends CollisionManager {
    private static final int MAXIMUM_NUMBER_OF_GAME_OBJECTS = 500;
    private final List<GameObject> gameObjects;
    private final List<GameObject> gameObjectsToBeAdded;
    private final List<GameObject> gameObjectsToBeRemoved;

    GameObjectManager() {
        gameObjects = new LinkedList<>();
        gameObjectsToBeAdded = new LinkedList<>();
        gameObjectsToBeRemoved = new LinkedList<>();
    }

    void add(GameObject gameObject) {
        gameObjectsToBeAdded.add(gameObject);
    }

    void remove(GameObject gameObject) {
        if (gameObjects.contains(gameObject)) {
            gameObjectsToBeRemoved.add(gameObject);
        }
    }

    void removeAll() {
        gameObjectsToBeAdded.clear();
        gameObjectsToBeRemoved.addAll(gameObjects);

    }

    void gameLoopUpdate() {
        updateLists();
        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            gameObject.updatePosition();
            gameObject.addToCanvas();
        }
        manageCollisions(false);
    }

    List<CollidingGameObject> provideAllActiveEnemies() {
        var enemies = new LinkedList<CollidingGameObject>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof EnemyGameObject enemyGameObject) {
                enemies.add(enemyGameObject);
            }
        }
        return enemies;
    }

    List<Astronaut> provideAllAstronauts() {
        var astronauts = new LinkedList<Astronaut>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Astronaut astronaut) {
                astronauts.add(astronaut);
            }
        }
        return astronauts;
    }

    List<RemainingLive> provideAllRemainingLives() {
        var remainingLives = new LinkedList<RemainingLive>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof RemainingLive remainingLive) {
                remainingLives.add(remainingLive);
            }
        }
        return remainingLives;
    }

    List<SmartBomb> provideAllRemainingSmartBombs() {
        var smartBombs = new LinkedList<SmartBomb>();
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof SmartBomb SmartBomb) {
                smartBombs.add(SmartBomb);
            }
        }
        return smartBombs;
    }

    private void updateLists() {
        removeFromGameObjects();
        addToGameObjects();
        if (gameObjects.size() > MAXIMUM_NUMBER_OF_GAME_OBJECTS) {
            throw new TooManyGameObjectsException(String.format("The maximum number of game objects (%d) has been exceeded (%d).", MAXIMUM_NUMBER_OF_GAME_OBJECTS, gameObjects.size()));
        }
    }

    private void removeFromGameObjects() {
        for (GameObject gameObject : gameObjectsToBeRemoved) {
            gameObjects.remove(gameObject);
            removeFromCollisionManagement(gameObject);
        }
        gameObjectsToBeRemoved.clear();
    }

    private void addToGameObjects() {
        for (GameObject toAdd : gameObjectsToBeAdded) {
            sortIntoGameObjects(toAdd);
            addToCollisionManagement(toAdd);
        }
        gameObjectsToBeAdded.clear();
    }

    private void sortIntoGameObjects(GameObject toAdd) {
        int indexToSortIn = 0;
        for (GameObject gameObject : gameObjects) {
            if (gameObject.getDistanceToBackground() >= toAdd.getDistanceToBackground()) {
                break;
            }
            indexToSortIn++;
        }
        gameObjects.add(indexToSortIn, toAdd);
    }
}
