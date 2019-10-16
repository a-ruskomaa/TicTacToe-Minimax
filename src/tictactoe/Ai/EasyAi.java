package tictactoe.Ai;

import tictactoe.Coordinate;
import tictactoe.Game;
import tictactoe.Player.Player;

import java.util.Random;

public class EasyAi implements Ai {
    private String level;
    private Player player;


    public EasyAi(Player player) {
        this.level = "easy";
        this.player = player;
    }

    @Override
    public Coordinate getNextCoordinates() {
        Game game = player.getGame();

        Random random = new Random();

        while (true) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);

            if (game.getCell(x, y) == ' ') {
                return new Coordinate(x, y);
            }
        }
    }

    @Override
    public String getLevel() {
        return this.level;
    }
}