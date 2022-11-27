package me.cedric.player;

import me.cedric.game.ChipState;
import me.cedric.game.EvaluationUtilities;
import me.cedric.game.Field;
import me.cedric.game.GameState;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            Field newField = this.deepCopyField(field);
            //Simulate Dropping of Friendly
            newField.dropChip(i, this.getChipState());
            if (EvaluationUtilities.evaluateBoard(this, enemy, newField) == GameState.PLAYER1WINNER) {
                return i;
            }
            newField.removeLastDropped(i);
            newField.dropChip(i, enemy.getChipState());
            if (EvaluationUtilities.evaluateBoard(this, enemy, newField) == GameState.PLAYER2WINNER) {
                return i;
            }
            newField.removeLastDropped(i);
            newField.dropChip(i, this.getChipState());


        }
        Random random = new Random();
        while (true) {
            //generate random number for next turn and check if the turn is possible
            int column = random.nextInt(field.getHorizontalSize());
            if (field.dropPossible(column)) {
                return column;
            }
        }
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
