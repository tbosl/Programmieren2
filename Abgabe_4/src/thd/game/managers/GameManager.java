package thd.game.managers;

import thd.game.utilities.GameView;

class GameManager extends UserControlledGameObjectPool {

    GameManager(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        astronaut.updatePosition();
        astronaut.addToCanvas();
        lander.updatePosition();
        lander.addToCanvas();
        score.addToCanvas();
        spaceship.updateStatus();
        spaceship.updatePosition();
        spaceship.addToCanvas();
        headerFrame.addToCanvas();
        scannerFrame.addToCanvas();
        smartBombReserve.addToCanvas();
        laserProjectile.updatePosition();
        laserProjectile.addToCanvas();
        bomber.updatePosition();
        bomber.addToCanvas();
        mutant.updatePosition();
        mutant.addToCanvas();
        bomberBomb.addToCanvas();
    }
}
