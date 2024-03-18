package game;

public class GameViewManager extends GameView {
    Lander lander;
    Astronaut astronaut;
    Score score;

    @Override
    public void initialize() {
        lander = new Lander(this);
        astronaut = new Astronaut(this);
        score = new Score(this);
    }

    @Override
    public void gameLoop() {
        lander.updatePosition();
        lander.addToCanvas();
        astronaut.updatePosition();
        astronaut.addToCanvas();
        score.addToCanvas();
    }
}