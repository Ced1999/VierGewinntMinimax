package me.cedric.player;

import me.cedric.game.ChipState;
import me.cedric.game.Field;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, char token, ChipState chipState) {
        super(name, token, chipState);
    }

    @Override
    public int doTurn() {
        System.out.println(this.getName() + " please choose a column for your turn: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();

    }

    @Override
    public int doTurn(Field field) {
        return 0;
    }
}
