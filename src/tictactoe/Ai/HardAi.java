package tictactoe.Ai;

import tictactoe.Coordinate;
import tictactoe.Player.Player;

public class HardAi implements Ai {
    private String level;
    private Player player;

    public HardAi(Player player) {
        this.level = "hard";
        this.player = player;
    }

    /** A simplified version of the minimax algorithm. This method is the starting point of the algorithm. */
    @Override
    public Coordinate getNextCoordinates() {
        //Let's ask for a copy of the current playfield for easier access
        char[][] playfield = player.getGame().checkPlayfield();

        char token = player.getToken();
        char opponentsToken;

        if (token == 'X') {
            opponentsToken = 'O';
        } else {
            opponentsToken = 'X';
        }

        //We'll store the best coordinates here
        Coordinate bestMove = new Coordinate();

        int bestScore = -1000;

        //used for debugging, this table can be used to show the highest possible score for every cell of the game
        //int[][] scoretable = new int[3][3];

        //We'll loop through every free cell and calculate the possible outcomes if we placed our token there
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (playfield[y][x] == ' ') {

                    playfield[y][x] = token;

                    /* The minimax method will return a number (10, 0 or -10) based on the best certain outcome for
                       that starting point. So if we can be sure that a move will result in our (the Ai's) victory no
                       matter what the opponent will do, we'll get a return value of 10. A certain draw will return a 0
                       and a certain defeat -10. Obviously we'll choose the cell with the highest possible value.
                     */
                    int moveScore = miniMax(playfield, opponentsToken, 1);

                    if (moveScore > bestScore) {
                        bestScore = moveScore;
                        bestMove = new Coordinate(x,y);
                    }

                    //scoretable[y][x] = moveScore;

                    //Let's undo the last move and start over from the next empty position
                    playfield[y][x] = ' ';
                }
            }
        }

//        printScoreTable(scoretable);

        return bestMove;
    }

    /** A simplified version of the minimax algorithm. This method is used recursively to analyze all possible outcomes of different moves.*/
    private int miniMax(char[][] playfield, char playerInTurn, int depth) {

        //First, let's check if we have reached a final state
        int finalScore = checkScore(playfield);

        //Return +10 for Ai's victory
        if (finalScore == 10) {
//            printPlayfield(playfield);
//            System.out.println("depth: " + depth);
//            System.out.println(finalScore);
            return finalScore;
        }

        //Return -10 for player's victory
        if (finalScore == -10) {
//            printPlayfield(playfield);
//            System.out.println("depth: " + depth);
//            System.out.println(finalScore);
            return finalScore;
        }

        //Return 0 for draw
        if (finalScore == 0 && !movesLeft(playfield)) {
//            printPlayfield(playfield);
//            System.out.println("depth: " + depth);
//            System.out.println(finalScore);
            return finalScore;
        }


        //Let's find out what token we are playing as
        char token = player.getToken();
        char opponentsToken;

        if (token == 'X') {
            opponentsToken = 'O';
        } else {
            opponentsToken = 'X';
        }

        int score = -1000;

        /* If it's the Ai's turn, lets go through all possible moves one by one
        *  and find out what the best certain outcome is. We are looking for a certain victory or at least a certain draw. */
        if (playerInTurn == token) {

            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (playfield[y][x] == ' ') {

                        //If the cell is empty, let's see what happens if we put our token there
                        playfield[y][x] = token;

                        /* Let's use this same method to find out what the best play for the Ai's opponent would be during next turn.
                        *  The method will return a score based on the best outcome for the opponent, so if this move
                        *  resulted in the opponents victory on the next turn, the returned score will be -10 */
                        int moveScore = miniMax(playfield, opponentsToken, depth + 1);

                        /* If the returned score was higher than the best score already returned from this branch, let's
                        store it.*/
                        if (moveScore > score) {
                            score = moveScore;
                        }

                        //Let's undo this move and try another on the next iteration
                        playfield[y][x] = ' ';
                    }
                }
            }

            /* If it's the opponent's turn, lets go through all possible moves one by one
            *  and find out what the best certain outcome is for the opponent */
        } else {
            score = 1000;

            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (playfield[y][x] == ' ') {

                        //Let's see what happens if our opponent would place their token here
                        playfield[y][x] = opponentsToken;

                        int moveScore = miniMax(playfield, token, depth + 1);

                        /* If the move resulted in a favorable outcome for the opponent (a victory or at least a draw)
                        let's store the value */
                        if (moveScore < score) {
                            score = moveScore;
                        }

                        playfield[y][x] = ' ';
                    }
                }
            }
        }

        //Return the smallest or the largest score, depending on who's turn it was
        return score;
    }

    /** Checks for any empty cells in the playfield, returns true if at least one is found*/
    private boolean movesLeft(char[][] playfield) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (playfield[y][x] == ' ') {
                    return true;
                }
            }
        }

        return false;
    }

    private int checkScore(char[][] p) {

        char token = player.getToken();

        char opponentsToken;

        if (token == 'X') {
            opponentsToken = 'O';
        } else {
            opponentsToken = 'X';
        }

        //check if the planned move resulted in an ended state
        //return 10 for Ai's win, -10 for players win

        //rows
        for (int y = 0; y < 3; y++) {
            if (p[y][0] == p[y][1] && p[y][1] == p[y][2]) {
                if (p[y][0] == token) {
                    return 10;
                }
                if (p[y][0] == opponentsToken) {
                    return -10;
                }
            }
        }


        //columns
        for (int x = 0; x < 3; x++) {
            if (p[0][x] == p[1][x] && p[1][x] == p[2][x]) {
                if (p[0][x] == token) {
                    return 10;
                }
                if (p[0][x] == opponentsToken) {
                    return -10;
                }
            }
        }

        //first diagonal
        if (p[0][0] == p[1][1] && p[1][1] == p[2][2]) {
            if (p[0][0] == token) {
                return 10;
            }
            if (p[0][0] == opponentsToken) {
                return -10;
            }
        }


        //second
        if (p[0][2] == p[1][1] && p[1][1] == p[2][0]) {
            if (p[0][2] == token) {
                return 10;
            }
            if (p[0][2] == opponentsToken) {
                return -10;
            }
        }

        return 0;
    }

    //for debugging purposes
    private void printPlayfield(char[][] playfield) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", playfield[0][0], playfield[0][1], playfield[0][2]);
        System.out.printf("| %c %c %c |%n", playfield[1][0], playfield[1][1], playfield[1][2]);
        System.out.printf("| %c %c %c |%n", playfield[2][0], playfield[2][1], playfield[2][2]);
        System.out.println("---------");
    }

    //for debugging purposes
    private void printScoreTable(int[][] playfield) {
        System.out.println("---------");
        System.out.printf("| %d %d %d |%n", playfield[0][0], playfield[0][1], playfield[0][2]);
        System.out.printf("| %d %d %d |%n", playfield[1][0], playfield[1][1], playfield[1][2]);
        System.out.printf("| %d %d %d |%n", playfield[2][0], playfield[2][1], playfield[2][2]);
        System.out.println("---------");
    }

    @Override
    public String getLevel() {
        return this.level;
    }

}