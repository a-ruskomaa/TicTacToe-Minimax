package tictactoe.Player;

import tictactoe.Ai.Ai;
import tictactoe.Coordinate;
import tictactoe.Game;

class AiPlayer implements Player {
    private char token;
    private Game game;
    private Ai ai;

    AiPlayer(char token) {
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

    public Ai getAi() {
        return ai;
    }

    void setAi(Ai ai) {
        this.ai = ai;
    }

    @Override
    public void move() {
        Coordinate coordinate = this.ai.getNextCoordinates();

        int x = coordinate.getX();
        int y = coordinate.getY();

        game.setCell(x, y, this.token);
        System.out.println("Making move level \"" + this.ai.getLevel() + "\"");
    }
}