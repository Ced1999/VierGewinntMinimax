package me.cedric.player;

public abstract class Player {
    private String name;
    private char token;
    public abstract int doTurn();

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
