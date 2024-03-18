package game;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this(0, 0);
    }

    public Position(Position otherPosition) {
        this(otherPosition.x, otherPosition.y);
    }

    public void left(double pixel) {
        x -= pixel;
    }

    public void left() {
        left(1);
    }


    public void right(double pixel) {
        x += pixel;
    }

    public void right() {
        right(1);
    }


    public void up(double pixel) {
        y -= pixel;
    }

    public void up() {
        up(1);
    }

    public void down(double pixel) {
        y += pixel;
    }

    public void down() {
        down(1);
    }

    void updateCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void updateCoordinates(Position otherPosition) {
        updateCoordinates(otherPosition.x, otherPosition.y);
    }

    @Override
    public String toString() {
        return String.format("Position (%d, %d)", (int) Math.round(x), (int) Math.round(y));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
