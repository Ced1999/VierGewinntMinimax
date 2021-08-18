package me.cedric.player;

import me.cedric.game.Field;

public abstract class Player {
    private String name;
    private char token;

    public abstract int doTurn();

    public abstract int doTurn(Field field);


    public Player(String name, char token) {
        this.name = name;
        this.token = token;
    }

    public char getToken() {
        return this.token;
    }

    public String getName() {
        return this.name;
    }
}
