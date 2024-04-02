package thd.game.managers;

import thd.gameobjects.movable.Astronaut;
import thd.gameobjects.movable.Lander;
import thd.gameobjects.unmovable.Score;
import thd.game.utilities.GameView;

class GameManager {
    private final Lander lander;
    private final Astronaut astronaut;
    private final Score score;

    GameManager(GameView gameView) {
        lander = new Lander(gameView);
        astronaut = new Astronaut(gameView);
        score = new Score(gameView);
    }

    void gameLoopUpdate() {
        lander.updatePosition();
        lander.addToCanvas();
        astronaut.updatePosition();
        astronaut.addToCanvas();
        score.addToCanvas();
    }
}
