package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

class GameManager extends GameWorldManager {

    GameManager(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }

    private void gameManagement() {
    }
}
