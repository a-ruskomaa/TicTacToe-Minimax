package tictactoe;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate() {
        this.x = -1;
        this.y = -1;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }
}