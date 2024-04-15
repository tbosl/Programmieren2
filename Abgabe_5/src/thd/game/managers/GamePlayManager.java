package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Square;

/**
 * Manages the creation and destruction of game objects.
 */
public class GamePlayManager extends UserControlledGameObjectPool {
    /**
     * Represents the amount of smart bombs that the user has available.
     */
    public int amountOfSmartBombs;

    /**
     * Represents the amount of remaining lives that the user has available.
     */
    public int amountOfRemainingLives;
    private final GameObjectManager gameObjectManager;
    private int currentNumberOfVisibleSquares;

    protected GamePlayManager(GameView gameView) {
        super(gameView);
        this.gameObjectManager = new GameObjectManager();
        amountOfSmartBombs = 0;
        amountOfRemainingLives = 0;
    }

    /**
     * Adds a new game object to the game.
     *
     * @param gameObject The game object to be added.
     */
    public void spawnGameObject(GameObject gameObject) {
        gameObjectManager.add(gameObject);
    }

    /**
     * Removes a new game object to the game.
     *
     * @param gameObject The game object to be removed.
     */
    public void destroyGameObject(GameObject gameObject) {
        gameObjectManager.remove(gameObject);
    }

    protected void destroyAllGameObjects() {
        gameObjectManager.removeAll();
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
        gamePlayManagement();
    }

    private void gamePlayManagement() {
        if (currentNumberOfVisibleSquares < 5 && gameView.timer(1000, this)) {
            spawnGameObject(new Square(gameView, this));
            currentNumberOfVisibleSquares++;
        }
    }
}
