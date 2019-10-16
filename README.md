# TicTacToe-Minimax
### A JetBrains Academy / Hyperskill.org practice project.

#### Project specification:
https://hyperskill.org/projects/81

The game is a command line version of the tic tac toe game.

You start the game by running the compiled version and typing 'start param1 param2'.

Accepted parameters are:
*'user' will create a human player
*'easy', 'medium' and 'hard' create an ai-player with the given difficulty level

So entering 'start user medium' will start the game with human as the first player (X) and medium ai as the second (O).

Easy ai will just make random moves.
Medium will try to win win or prevent the player from winning during the next round. If it can't, it will make a random move.
Hard ai is based on a simplified minimax-algorithm and will always beat the player or force a draw.

Entering 'exit' outside of a game will quit the program.
