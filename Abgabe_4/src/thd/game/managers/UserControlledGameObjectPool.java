package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {
    protected final GameView gameView;
    protected Astronaut astronaut;
    protected Lander lander;
    protected Score score;
    protected Spaceship spaceship;
    protected HeaderFrame headerFrame;
    protected ScannerFrame scannerFrame;
    protected SmartBombReserve smartBombReserve;
    protected LaserProjectile laserProjectile;
    protected Bomber bomber;
    protected Mutant mutant;
    protected BomberBomb bomberBomb;
    protected Pod pod;
    protected Swarmer swarmer;
    protected Baiter baiter;
    protected EnemyProjectile enemyProjectile;

    protected UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
    }

    protected void gameLoopUpdate() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys();
        for (int keyCode : pressedKeys) {
            processKeyCode(keyCode);
        }
    }

    private void processKeyCode(int keyCode) {
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

