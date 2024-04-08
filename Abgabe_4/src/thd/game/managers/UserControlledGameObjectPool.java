package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.Astronaut;
import thd.gameobjects.movable.Lander;
import thd.gameobjects.movable.LaserProjectile;
import thd.gameobjects.movable.Spaceship;
import thd.gameobjects.unmovable.HeaderFrame;
import thd.gameobjects.unmovable.ScannerFrame;
import thd.gameobjects.unmovable.Score;
import thd.gameobjects.unmovable.SmartBombReserve;

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
