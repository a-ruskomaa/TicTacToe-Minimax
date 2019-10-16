package tictactoe.Ai;

import tictactoe.Coordinate;

public interface Ai {

    Coordinate getNextCoordinates();

    String getLevel();
}