package tictactoe.Ai;

import tictactoe.Coordinate;
import tictactoe.Game;
import tictactoe.Player.Player;

import java.util.Random;

public class MediumAi implements Ai {
    private String level;
    private Player player;

    public MediumAi(Player player) {
        this.level = "medium";
        this.player = player;
    }

    @Override
    public Coordinate getNextCoordinates() {
        Game game = player.getGame();

        char token = player.getToken();

        //check for ai's winning move, return coordinates if found
        Coordinate coordinate = checkForWinningMoves(token);
        if (coordinate != null) {
            return coordinate;
        }

        char opponentsToken;

        if (token == 'X') {
            opponentsToken = 'O';
        } else {
            opponentsToken = 'X';
        }

        //check for opponents winning move, return coordinates if found
        coordinate = checkForWinningMoves(opponentsToken);
        if (coordinate != null) {
            return coordinate;
        }

        //make a random move

        Random random = new Random();

        while (true) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);

            if (game.getCell(x, y) == ' ') {
                return new Coordinate(x, y);
            }
        }
    }

    private Coordinate checkForWinningMoves(char token) {

        char[][] playfield = player.getGame().checkPlayfield();

        //check for possible winning move

        //rows
        for (int y = 0; y < 3; y++) {
            if (playfield[y][0] == token && playfield[y][1] == token && playfield[y][2] == ' ') {
                return new Coordinate(2, y);
            }
            if (playfield[y][0] == token && playfield[y][1] == ' ' && playfield[y][2] == token) {
                return new Coordinate(1, y);
            }
            if (playfield[y][0] == ' ' && playfield[y][1] == token && playfield[y][2] == token) {
                return new Coordinate(0, y);
            }
        }

        //columns
        for (int x = 0; x < 3; x++) {
            if (playfield[0][x] == token && playfield[1][x] == token && playfield[2][x] == ' ') {
                return new Coordinate(x, 2);
            }
            if (playfield[0][x] == token && playfield[1][x] == ' ' && playfield[2][x] == token) {
                return new Coordinate(x, 1);
            }
            if (playfield[0][x] == ' ' && playfield[1][x] == token && playfield[2][x] == token) {
                return new Coordinate(x, 0);
            }
        }

        //first diagonal
        if (playfield[0][0] == token && playfield[1][1] == token && playfield[2][2] == ' ') {
            return new Coordinate(2, 2);
        }
        if (playfield[0][0] == token && playfield[1][1] == ' ' && playfield[2][2] == token) {
            return new Coordinate(1, 1);
        }
        if (playfield[0][0] == ' ' && playfield[1][1] == token && playfield[2][2] == token) {
            return new Coordinate(0, 0);
        }

        //second
        if (playfield[0][2] == token && playfield[1][1] == token && playfield[2][0] == ' ') {
            return new Coordinate(0, 2);
        }
        if (playfield[0][2] == token && playfield[1][1] == ' ' && playfield[2][0] == token) {
            return new Coordinate(1, 1);
        }
        if (playfield[0][2] == ' ' && playfield[1][1] == token && playfield[2][0] == token) {
            return new Coordinate(2, 0);
        }

        return null;
    }

    @Override
    public String getLevel() {
        return this.level;
    }

}