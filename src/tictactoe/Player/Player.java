package tictactoe.Player;

import tictactoe.Game;

public interface Player {

    void move();

    char getToken();

    Game getGame();

    void setGame(Game game);

}