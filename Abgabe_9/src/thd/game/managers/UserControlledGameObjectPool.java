package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.Spaceship;
import thd.gameobjects.unmovable.Score;

import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {
    protected final GameView gameView;
    protected Score score;
    protected Spaceship spaceship;
    protected Level level;

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
        } else if (keyCode == KeyEvent.VK_B) {
            spaceship.detonateSmartBomb();
        }
    }

    /**
     * Get the player's spaceship.
     *
     * @return The player's spaceship.
     */
    public Spaceship getSpaceship() {
        return spaceship;
    }

    /**
     * Get the current level.
     *
     * @return The current level.
     */
    public Level getLevel() {
        return level;
    }
}

