package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

class GameManager extends GamePlayManager {

    GameManager(GameView gameView) {
        super(gameView);
        astronaut = new Astronaut(gameView, this);
        score = new Score(gameView, this);
        spaceship = new Spaceship(gameView, this);
        lander = new Lander(gameView, this, astronaut, spaceship);
        bomber = new Bomber(gameView, this);
        pod = new Pod(gameView, this, spaceship);
        baiter = new Baiter(gameView, this, spaceship);
        spawnGameObject(astronaut);
        spawnGameObject(astronaut);
        spawnGameObject(lander);
        spawnGameObject(score);
        spawnGameObject(spaceship);
        spawnGameObject(new HeaderFrame(gameView, this));
        spawnGameObject(new ScannerFrame(gameView, this));
        for (int spawnedSmartBombs = 0; spawnedSmartBombs < SmartBomb.AMOUNT_OF_SMART_BOMBS_AT_START; spawnedSmartBombs++) {
            spawnGameObject(new SmartBomb(gameView, this));
        }
        for (int spawnedRemainingLives = 0; spawnedRemainingLives < RemainingLive.AMOUNT_OF_LIVES_START; spawnedRemainingLives++) {
            spawnGameObject((new RemainingLive(gameView, this)));
        }
        spawnGameObject(bomber);
        spawnGameObject(pod);
        spawnGameObject(baiter);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }

    private void gameManagement() {
    }
}
