package me.cedric.player;

import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(String name,char token) {
        super(name,token);
    }

    @Override
    public int doTurn() {
        System.out.println(this.getName() + " please choose a column for your turn: ");
        Scanner input = new Scanner(System.in);
        int turn = input.nextInt();
        return turn;

    }
}
