package thd.game.managers;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;
import java.util.List;

class GameObjectManager {
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
        if (!gameObjects.contains(gameObject)) {
            gameObjectsToBeAdded.add(gameObject);
        }
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
    }

    private void updateLists() {
        removeFromGameObjects();
        addToGameObjects();
        if (gameObjects.size() > MAXIMUM_NUMBER_OF_GAME_OBJECTS) {
            throw new TooManyGameObjectsException(String.format("The maximum number of game objects (%d) has been exceeded (%d).", MAXIMUM_NUMBER_OF_GAME_OBJECTS, gameObjects.size()));
        }
    }

    private void removeFromGameObjects() {
        gameObjects.removeAll(gameObjectsToBeRemoved);
        gameObjectsToBeRemoved.clear();
    }

    private void addToGameObjects() {
        gameObjects.addAll(gameObjectsToBeAdded);
        gameObjectsToBeAdded.clear();
    }
}
