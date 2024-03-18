package game;

import java.awt.*;

public class Lander {
    private GameView gameView;
    private Position position;
    private double speedInPixel;
    private double rotation;
    private double size;
    private double width;
    private double height;

    public Lander(GameView gameView){
        this.gameView = gameView;
        size = 30;
        position = new Position(0, GameView.HEIGHT / 2);
        speedInPixel = 5;
        rotation = 0;
        width = 150;
        height = 33;
    }

    @Override
    public String toString(){
        return "LANDER: " + position;
    }

    public void updatePosition(){
         position.right(speedInPixel);
         rotation++;
    }

    public void addToCanvas(){
        gameView.addTextToCanvas("Objekt 1", position.getX(), position.getY(),
                size, true, Color.YELLOW, rotation);
    }
}
