package game;

import java.awt.*;

public class Score {
    private GameView gameView;
    private Position position;
    private double speedInPixel;
    private double rotation;
    private double size;
    private double width;
    private double height;

    public Score(GameView gameView) {
        this.gameView = gameView;
        size = 30;
        speedInPixel = 0;
        rotation = 0;
        width = 150;
        height = 33;
        position = new Position(GameView.WIDTH - width, -8);
    }

    @Override
    public String toString() {
        return "SCORE: " + position;
    }

    public void addToCanvas() {
        gameView.addTextToCanvas("Objekt 3", position.getX(), position.getY(),
                size, true, Color.YELLOW, rotation);
    }
}
