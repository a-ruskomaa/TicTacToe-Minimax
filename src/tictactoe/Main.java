package tictactoe;

import tictactoe.Player.Player;
import tictactoe.Player.PlayerFactory;

import java.util.Scanner;

public class Main {
    public static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        Player player1;
        Player player2;

        while (true) {

            System.out.print("Input command: ");

            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            String[] params = input.split(" ");

            if (params[0].equals("start") && params.length == 3) {
                player1 = PlayerFactory.createPlayer(params[1], 'X');
                player2 = PlayerFactory.createPlayer(params[2], 'O');
                if (player1 != null && player2 != null) {
                    runTheGame(player1, player2);
                } else {
                    System.out.println("Bad parameters!");
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }

    }

    private static void runTheGame(Player player1, Player player2) {
        Game game = new Game(player1, player2);

        game.printPlayfield();

        do {
            game.nextMove();
        } while (!game.checkState());
    }
}


