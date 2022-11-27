package me.cedric.player;

import me.cedric.game.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class MiniMaxAI extends Player {

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
        this.enemy = enemy;
        int move=  findBestMove(field, this);
        if (field.dropPossible(move)) {
            return move;
        }
        return -1000;
    }


    private int miniMax(Field field, Player player, int depth) {
        Field copiedfield = deepCopyField(field);
        List<Integer> validLocations = field.getValidLocations();
        if (depth == 0 || !(copiedfield.anyMoreMovesPossible())) {
            return value(copiedfield, this, enemy);
        }
        if (player == this) {
            int maxEval = Integer.MIN_VALUE;
            for (int i : copiedfield.getValidLocations()) {
                Field newField = makeMove(copiedfield, i, this.getChipState());
                int eval = miniMax(newField, this.enemy, depth - 1);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i : copiedfield.getValidLocations()) {
                Field newField = makeMove(field, i, this.enemy.getChipState());
                int eval = miniMax(newField, this, depth - 1);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }

    }


    private int findBestMove(Field field, Player player) {
        int bestMove = -100;
        int bestValue = Integer.MIN_VALUE;
        for (int move : field.getValidLocations()) {
            int eval = (miniMax(makeMove(field, move, player.getChipState()), player, 5));
            if (eval > bestValue) {
                bestMove = move;
                bestValue = eval;
            }

        }
        return bestMove;

    }

    private int value(Field field, Player ai, Player enemy) {
        //Value Function still needs more heuristics for 2 connected 3 connected etc
        if (EvaluationUtilities.evaluateBoard(ai, enemy, field) == GameState.PLAYER1WINNER) {
            return +1000;
        }
        if (EvaluationUtilities.evaluateBoard(ai, enemy, field) == GameState.PLAYER2WINNER) {
            return -1000;
        }
        return 0;
    }

    public Field makeMove(Field field, int column, ChipState chipState) {
        Field newField = deepCopyField(field);
        newField.dropChip(column, chipState);
        return newField;
    }

    private Field deepCopyField(Field field) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(field);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            return (Field) new ObjectInputStream(bais).readObject();
        } catch (Exception exception) {
            return field;
        }
    }

}
