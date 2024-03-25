package thd.game.managers;

import thd.gameobjects.movable.Astronaut;
import thd.gameobjects.movable.FollowerBall;
import thd.gameobjects.movable.Lander;
import thd.gameobjects.movable.RandomBall;
import thd.gameobjects.unmovable.Score;
import thd.game.utilities.GameView;

class GameManager {
    private final Astronaut astronaut;
    private final Lander lander;
    private final Score score;
    private final RandomBall randomBall;
    private final FollowerBall followerBall;

    GameManager(GameView gameView) {
        astronaut = new Astronaut(gameView);
        lander = new Lander(gameView, astronaut);
        score = new Score(gameView);
        randomBall = new RandomBall(gameView);
        followerBall = new FollowerBall(gameView, randomBall);
    }

    void gameLoopUpdate() {
        astronaut.updatePosition();
        astronaut.addToCanvas();
        lander.updatePosition();
        lander.addToCanvas();
        score.addToCanvas();
        randomBall.updatePosition();
        randomBall.addToCanvas();
        followerBall.updatePosition();
        followerBall.addToCanvas();
    }
}
