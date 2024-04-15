package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

class GameManager extends UserControlledGameObjectPool {
    private final GameObjectManager gameObjectManager;


    GameManager(GameView gameView) {
        super(gameView);
        this.gameObjectManager = new GameObjectManager();
        astronaut = new Astronaut(gameView);
        lander = new Lander(gameView, astronaut);
        score = new Score(gameView);
        spaceship = new Spaceship(gameView);
        scannerFrame = new ScannerFrame(gameView);
        smartBombReserve = new SmartBombReserve(gameView);
        headerFrame = new HeaderFrame(gameView);
        laserProjectile = new LaserProjectile(gameView, spaceship);
        bomber = new Bomber(gameView);
        mutant = new Mutant(gameView, spaceship, lander);
        bomberBomb = new BomberBomb(gameView);
        pod = new Pod(gameView);
        swarmer = new Swarmer(gameView, spaceship, pod);
        baiter = new Baiter(gameView, spaceship);
        enemyProjectile = new EnemyProjectile(gameView, lander, spaceship.getPosition());
        gameObjectManager.add(astronaut);
        gameObjectManager.add(lander);
        gameObjectManager.add(score);
        gameObjectManager.add(spaceship);
        gameObjectManager.add(headerFrame);
        gameObjectManager.add(scannerFrame);
        gameObjectManager.add(smartBombReserve);
        gameObjectManager.add(laserProjectile);
        gameObjectManager.add(bomber);
        gameObjectManager.add(mutant);
        gameObjectManager.add(bomberBomb);
        gameObjectManager.add(pod);
        gameObjectManager.add(swarmer);
        gameObjectManager.add(baiter);
        gameObjectManager.add(enemyProjectile);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
    }
}
