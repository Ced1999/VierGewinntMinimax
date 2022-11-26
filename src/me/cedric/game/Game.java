package me.cedric.game;

import me.cedric.player.HumanPlayer;
import me.cedric.player.Player;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Field gameField;
    private GameState gameState;
    private Player turn;
    private final int waitingTime; //Time in ms to sleep between each Output (better visualization of bot games)
    private final boolean showOutput; //Enable or Disable output (Mainly useful for performance of bot games)

    public Game(Player player1, Player player2, int height, int width, int waitingTime, boolean showOutput) {
        this.player1 = player1;
        this.player2 = player2;
        this.waitingTime = waitingTime;
        this.showOutput = showOutput;
        this.gameField = new Field(height, width);
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 0) {
            turn = player1;
        } else {
            turn = player2;
        }
        this.gameState = GameState.PREPARATION;
    }

    public void startGame() {
        this.gameState = GameState.RUNNING;
        gameLogic();
    }

    public void gameLogic() {
        while (this.gameState == GameState.RUNNING) {
            if (showOutput) {
                System.out.println("This is the current field:");
                this.gameField.printField();
            }
            //While Loop Until Player has made a valid Turn
            while (true) {
                try {
                    sleep(this.waitingTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (showOutput) {
                    System.out.println("It is " + turn.getName() + "'s turn: ");
                }
                int column;
                if (turn instanceof HumanPlayer) {
                    column = turn.doTurn();
                } else {
                    column = turn.doTurn(gameField);
                }
                //Check if column to Drop is valid and drop chip depending on which players turn it is
                if (this.gameField.dropPossible(column)) {
                    if (turn == player1) {
                        this.gameField.dropChip(column, ChipState.RED);
                        turn = player2;
                        break;
                    }
                    if (turn == player2) {
                        this.gameField.dropChip(column, ChipState.BLUE);
                        turn = player1;
                        break;
                    }
                } else {
                    if (showOutput) {
                        System.out.println("No more Chips can fit in this column please choose another column");
                    }
                }
            }
            //After every turn evaluate the Board State to check if a winner has been determined

            this.gameState = EvaluationUtilities.evaluateBoard(player1, player2, gameField);
        }
        if (showOutput) {
            System.out.println("This is the final state of the board: 0");
            this.gameField.printField();
        }
        if (this.gameState == GameState.PLAYER1WINNER) {
            endGame(player1);
        }
        if (this.gameState == GameState.PLAYER2WINNER) {
            endGame(player2);
        }
        if (this.gameState == GameState.REMIS) {
            endGame(player2);
        }


    }

    public void endGame(Player winner) {
        if (showOutput) {
            System.out.println("The game is finished. The Winner is: " + winner.getName());
        }
    }
    public void endGame() {
        if (showOutput) {
            System.out.println("The game is finished. No Winner could be determined");

        }
    }



    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Field getGameField() {
        return gameField;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getTurn() {
        return turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }
}
