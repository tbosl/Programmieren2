package game;

import java.awt.*;

public class Astronaut {
    private GameView gameView;
    private Position position;
    private double speedInPixel;
    private double rotation;
    private double size;
    private double width;
    private double height;

    public Astronaut(GameView gameView) {
        this.gameView = gameView;
        size = 30;
        position = new Position(1100, 650);
        speedInPixel = 2;
        rotation = 0;
        width = 150;
        height = 33;
    }

    @Override
    public String toString() {
        return "ASTRONAUT: " + position;
    }

    public void updatePosition() {
        position.left(speedInPixel);
    }

    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.getX() - 3, position.getY() + 3, width, height, 0, true, Color.GREEN);
        gameView.addRectangleToCanvas(position.getX() - 3, position.getY() + 3, width, height, 5, false, Color.WHITE);

        gameView.addTextToCanvas("Objekt 2", position.getX(), position.getY(),
                size, true, Color.BLUE, rotation);
    }
}
