package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MainCharacter;
import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.unmovable.BomberBomb;

import java.util.ArrayList;
import java.util.List;

/**
 * A gameobject that represents the spaceship controlled by the player.
 */
public class Spaceship extends CollidingGameObject implements MainCharacter {
    private final int shotDurationInMilliseconds;
    private boolean shotAvailable;
    private boolean smartBombDetonatable;
    private static final int SMART_BOMB_COOLDOWN_IN_MS = 1000;

    /**
     * Creates the spaceship with a reference of the gameview.
     *
     * @param gameView        The GameView
     * @param gamePlayManager The manager which is responsible for the occurrence of the spaceship.
     */
    public Spaceship(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        double horizontalPositionFactor = 0.3;
        double verticalPositionFactor = 0.5;
        position.updateCoordinates(GameView.WIDTH * horizontalPositionFactor, GameView.HEIGHT * verticalPositionFactor);
        size = 0.1;
        speedInPixel = 7;
        width = 75;
        height = 16;
        shotDurationInMilliseconds = 300;
        shotAvailable = true;
        smartBombDetonatable = true;
        int hitBoxOffsetY = 12;
        hitBoxOffsets(0, hitBoxOffsetY, 0, 0);
    }

    /**
     * Moves the spaceship to the left at the current speed.
     */
    public void left() {
        position.left(speedInPixel);
    }

    /**
     * Moves the spaceship to the right at the current speed.
     */
    public void right() {
        position.right(speedInPixel);
    }

    /**
     * Moves the spaceship to the top at the current speed.
     */
    public void up() {
        position.up(speedInPixel);
        if (position.getY() < MovementPattern.UPPER_BOUNDARY) {
            position.updateCoordinates(position.getX(), MovementPattern.UPPER_BOUNDARY);
        }
    }

    /**
     * Moves the spaceship to the bottom at the current speed.
     */
    public void down() {
        position.down(speedInPixel);
        if (position.getY() > MovementPattern.LOWER_BOUNDARY) {
            position.updateCoordinates(position.getX(), MovementPattern.LOWER_BOUNDARY);
        }
    }

    /**
     * Shoots a projectile towards the direction the spaceship is currently facing.
     */
    @Override
    public void shoot() {
        updateShotAvailable();
        if (shotAvailable) {
            gamePlayManager.spawnGameObject(new LaserProjectile(gameView, gamePlayManager, position));
            shotAvailable = false;
        }
    }

    /**
     * Detonates a smart bomb to kill all enemies, which are currently visible on the screen.
     */
    public void detonateSmartBomb() {
        if (gameView.timer(SMART_BOMB_COOLDOWN_IN_MS, this) && gamePlayManager.getAmountOfSmartBombs() > 0) {
            smartBombDetonatable = true;
        }
        if (smartBombDetonatable) {
            gamePlayManager.detonateSmartBomb();
            List<EnemyGameObject> killedEnemies = killAllEnemiesOnScreen();
            updateScore(killedEnemies);
            smartBombDetonatable = false;
        }
    }

    private List<EnemyGameObject> killAllEnemiesOnScreen() {
        var killedEnemies = new ArrayList<EnemyGameObject>();
        List<CollidingGameObject> enemies = gamePlayManager.provideAllActiveEnemies();
        for (CollidingGameObject enemy : enemies) {
            double x = enemy.getPosition().getX();
            double y = enemy.getPosition().getY();
            if (x >= 0 && x <= GameView.WIDTH && y >= 0 && y <= GameView.HEIGHT) {
                gamePlayManager.destroyGameObject(enemy);
                killedEnemies.add((EnemyGameObject) enemy);
            }
        }
        return killedEnemies;
    }

    private void updateScore(List<EnemyGameObject> killedEnemies) {
        for (EnemyGameObject enemy : killedEnemies) {
            gamePlayManager.addPoints(enemy.getPointsOnDestruction());
        }
    }

    private void updateShotAvailable() {
        shotAvailable = shotAvailable || gameView.timer(shotDurationInMilliseconds, this);
    }

    @Override
    public String toString() {
        return "SPACESHIP: " + position;
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("spaceship_right.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof BomberBomb || other instanceof EnemyGameObject) {
            gamePlayManager.lifeLost();
        }
    }
}
