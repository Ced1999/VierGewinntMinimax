package me.cedric.player;

import me.cedric.game.ChipState;
import me.cedric.game.Field;

import java.util.Random;

public class RandomAI extends Player {


    @Override
    public int doTurn() {
        return 0;
    }

    public RandomAI(String name, char token, ChipState chipState) {
        super(name, token, chipState);
    }

    @Override
    public int doTurn(Field field) {
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
