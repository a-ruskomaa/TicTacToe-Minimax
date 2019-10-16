package tictactoe;

import tictactoe.Player.Player;

import java.util.Scanner;

public class Game {
    private static char[][] playfield;
    private Scanner scanner = Main.scanner;
    private Player player1;
    private Player player2;
    private Player activePlayer;

    Game(Player player1, Player player2) {
        initializePlayfield();

        player1.setGame(this);
        this.player1 = player1;

        player2.setGame(this);
        this.player2 = player2;

        activePlayer = player1;
    }


    private void initializePlayfield() {
        playfield = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    //for debugging purposes
    private static void initializePlayfield(String cells) {
        playfield = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char next = cells.charAt((i * 3) + j);
                if (next == '_') next = ' ';
                playfield[i][j] = next;
            }
        }
    }

    void nextMove() {
        activePlayer.move();
        printPlayfield();
        switchActivePlayer();
    }


    private void switchActivePlayer() {
        if (activePlayer == player1) {
            activePlayer = player2;
        } else {
            activePlayer = player1;
        }
    }

    public char[][] checkPlayfield() {
        char[][] copy = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = playfield[i][j];
            }
        }

        return copy;
    }


    public char getCell(int x, int y) {
        return playfield[y][x];
    }


//    void setCell(Coordinate c, char token) {
//
//    }

    public boolean setCell(int x, int y, char token) {
        if (this.getCell(x, y) != ' ') {
            return false;
        }
        playfield[y][x] = token;
        return true;
    }


    void printPlayfield() {
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", playfield[0][0], playfield[0][1], playfield[0][2]);
        System.out.printf("| %c %c %c |%n", playfield[1][0], playfield[1][1], playfield[1][2]);
        System.out.printf("| %c %c %c |%n", playfield[2][0], playfield[2][1], playfield[2][2]);
        System.out.println("---------");
    }


    boolean checkState() {
        boolean xWins = false;
        boolean oWins = false;
        boolean impossible = false;

        int xCount = 0;
        int oCount = 0;
        int emptyCount = 0;


        //Count cells and check if impossible
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (playfield[i][j] == 'X') {
                    xCount++;
                } else if (playfield[i][j] == 'O') {
                    oCount++;
                } else if (playfield[i][j] == ' ') {
                    emptyCount++;
                }
            }
        }

        if (Math.abs(xCount - oCount) > 1) {
            impossible = true;
        }

        //check rows for wins
        for (int i = 0; i < 3; i++) {
            if (playfield[i][0] == playfield[i][1] && playfield[i][1] == playfield[i][2]) {
                char winner = playfield[i][0];
                if (winner == 'X') {
                    xWins = true;
                } else if (winner == 'O') {
                    oWins = true;
                }
            }
        }

        //check columns for wins
        for (int i = 0; i < 3; i++) {
            if (playfield[0][i] == playfield[1][i] && playfield[1][i] == playfield[2][i]) {
                char winner = playfield[0][i];
                if (winner == 'X') {
                    xWins = true;
                } else if (winner == 'O') {
                    oWins = true;
                }
            }
        }

        //check diagonals for wins
        if (playfield[0][0] == playfield[1][1] && playfield[1][1] == playfield[2][2]) {
            char winner = playfield[0][0];
            if (winner == 'X') {
                xWins = true;
            } else if (winner == 'O') {
                oWins = true;
            }
        }
        if (playfield[0][2] == playfield[1][1] && playfield[1][1] == playfield[2][0]) {
            char winner = playfield[0][2];
            if (winner == 'X') {
                xWins = true;
            } else if (winner == 'O') {
                oWins = true;
            }
        }

        if (xWins && oWins) {
            impossible = true;
        }

        if (impossible) {
            System.out.println("Impossible");
            return true;
        }

        if (xWins) {
            System.out.println("X wins");
            return true;
        }

        if (oWins) {
            System.out.println("O wins");
            return true;
        }

        if (emptyCount == 0) {
            System.out.println("Draw");
            return true;
        }

        return false;
    }

}