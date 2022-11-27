package me.cedric.player;

import me.cedric.game.ChipState;
import me.cedric.game.Field;

import java.io.IOException;

public abstract class Player {
    private final String name;
    private final char token;

    public ChipState getChipState() {
        return chipState;
    }

    private final ChipState chipState;

    public abstract int doTurn();

    public abstract int doTurn(Field field);

    public abstract int doTurn(Field field, Player enemy) throws IOException, ClassNotFoundException;


    public Player(String name, char token, ChipState chipState) {
        this.name = name;
        this.token = token;
        this.chipState = chipState;
    }

    public char getToken() {
        return this.token;
    }

    public String getName() {
        return this.name;
    }
}
