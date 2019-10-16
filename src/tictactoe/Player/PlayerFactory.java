package tictactoe.Player;

import tictactoe.Ai.Ai;
import tictactoe.Ai.EasyAi;
import tictactoe.Ai.HardAi;
import tictactoe.Ai.MediumAi;

public abstract class PlayerFactory {

    public static Player createPlayer(String playerParam, char token) {
        switch (playerParam) {
            case "user":
                return new HumanPlayer(token);
            case "easy": {
                AiPlayer player = new AiPlayer(token);
                Ai ai = new EasyAi(player);
                player.setAi(ai);
                return player;
            }
            case "medium": {
                AiPlayer player = new AiPlayer(token);
                Ai ai = new MediumAi(player);
                player.setAi(ai);
                return player;
            }
            case "hard": {
                AiPlayer player = new AiPlayer(token);
                Ai ai = new HardAi(player);
                player.setAi(ai);
                return player;
            }
        }
        return null;
    }
}