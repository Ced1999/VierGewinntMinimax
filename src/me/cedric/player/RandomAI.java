package me.cedric.player;

import me.cedric.game.Field;

import java.util.Random;

public class RandomAI extends Player {


    @Override
    public int doTurn() {
        return 0;
    }

    public RandomAI(String name, char token) {
        super(name, token);
    }

    @Override
    public int doTurn(Field field) {
        Random random = new Random();
        while (true) {
            int column = random.nextInt(7);
            if (field.dropPossible(column)) {
                return column;
            }
        }
    }
}
