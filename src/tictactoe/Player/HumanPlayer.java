package tictactoe.Player;

import tictactoe.Game;
import tictactoe.Main;

import java.util.Scanner;

class HumanPlayer implements Player {
    private char token;
    private Game game;
    private Scanner scanner = Main.scanner;

    HumanPlayer(char token) {
        this.token = token;
    }

    public char getToken() {
        return this.token;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void move() {

        int x;
        int y;

        while (true) {
            System.out.print("Enter the coordinates: ");
            try {
                String[] line = scanner.nextLine().split(" ");
                x = Integer.parseInt(line[0]);
                y = Integer.parseInt(line[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers");
                continue;
            }


            if (x < 1 || x > 3 || y < 0 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            //Now the lower left coordinate is (x = 1, y = 1), let's convert that to y = [2], x = [0]
            x = x - 1;
            y = 3 - y;

            if (!game.setCell(x, y, this.token)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            break;
        }
    }
}
