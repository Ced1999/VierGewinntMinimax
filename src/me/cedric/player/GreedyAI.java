package me.cedric.player;

import me.cedric.game.ChipState;
import me.cedric.game.EvaluationUtilities;
import me.cedric.game.Field;
import me.cedric.game.GameState;

import java.util.Random;

public class GreedyAI extends Player {
    public GreedyAI(String name, char token, ChipState chipState) {
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

    public int doTurn(Field field, Player enemy) {
        for (int i : field.getValidLocations()) {
            Field newField = field;
            newField.dropChip(i, this.getChipState());
            if (EvaluationUtilities.evaluateBoard(this, enemy, newField) == GameState.PLAYER1WINNER) {
                return i;
            } else {
                Random random = new Random();
                while (true) {
                    //generate random number for next turn and check if the turn is possible
                    int column = random.nextInt(7);
                    if (field.dropPossible(column)) {
                        return column;
                    }
                }
            }
        }
        return 0;
    }

}
