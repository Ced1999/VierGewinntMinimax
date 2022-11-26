package me.cedric.player;

import me.cedric.game.ChipState;
import me.cedric.game.EvaluationUtilities;
import me.cedric.game.Field;
import me.cedric.game.GameState;

public class MiniMaxAI extends Player {
    private Player ai;
    private Player enemy;

    public MiniMaxAI(String name, char token, ChipState chipState) {
        super(name, token, chipState);
    }

    @Override
    public int doTurn() {
        return 0;
    }

    @Override
    public int doTurn(Field field) {
        return 0;
    }

    @Override
    public int doTurn(Field field, Player enemy) {
        return 0;
    }

    /*
       private int miniMax(Field field, Player player, int depth) {
           Set<Integer> validLocations = field.getValidLocations();
           if(depth==0 ||!field.anyMoreMovesPossible()) {
               return value();
           }
           int maxValue = Integer.MIN_VALUE;
                   int value;
                   if (player == this.ai) { //Maximizing Player
                       value= Integer.MIN_VALUE;

                       makeMove(field,i,ai.getChipState());
                       value = min(field,enemy,depth-1);
                       undoMove(field,i);
                   } else if(player == this.enemy) {
                       makeMove(field,i,enemy.getChipState());
                       value = min(field,ai,depth-1);
                       undoMove(field,i);
                   }
                   if(value>maxValue) {
                       maxValue=value;
                   }




       }

     */
    private int min(Field field, Player player, int depth) {
        return 0;
    }

    private int value() {
        return 0;
    }

    public boolean isWinningMove(Field field, int col, ChipState state) {
        field = makeMove(field, col, state);
        if (EvaluationUtilities.evaluateBoard(ai, enemy, field) == GameState.PLAYER1WINNER || EvaluationUtilities.evaluateBoard(ai, enemy, field) == GameState.PLAYER2WINNER) {
            return true;
        }
        return false;
    }

    public Field makeMove(Field field, int column, ChipState chipState) {
        field.dropChip(column, chipState);
        return field;
    }

    public Field undoMove(Field field, int lastColumn) {
        field.removeLastDropped(lastColumn);
        return field;
    }

    public boolean isTerminalBoard() {
        //return this.field.anyMoreMovesPossible() ||
        return false;
    }

}
