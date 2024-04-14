package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {
    protected final GameView gameView;
    protected final Astronaut astronaut;
    protected final Lander lander;
    protected final Score score;
    protected final Spaceship spaceship;
    protected final HeaderFrame headerFrame;

    protected final ScannerFrame scannerFrame;
    protected final SmartBombReserve smartBombReserve;
    protected final LaserProjectile laserProjectile;
    protected final Bomber bomber;
    protected final Mutant mutant;
    protected final BomberBomb bomberBomb;
    protected final Pod pod;
    protected final Swarmer swarmer;
    protected final Baiter baiter;

    protected UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
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
    }

    protected void gameLoopUpdate() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys();
        for (int keyCode : pressedKeys) {
            if (keyCode == KeyEvent.VK_A) {
                spaceship.left();
            } else if (keyCode == KeyEvent.VK_D) {
                spaceship.right();
            } else if (keyCode == KeyEvent.VK_W) {
                spaceship.up();
            } else if (keyCode == KeyEvent.VK_S) {
                spaceship.down();
            } else if (keyCode == KeyEvent.VK_SPACE) {
                spaceship.shoot();
            }
        }
    }
}
