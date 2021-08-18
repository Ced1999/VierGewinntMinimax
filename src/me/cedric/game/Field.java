package me.cedric.game;

import java.util.Arrays;

public class Field {
    private final char[][] field;
    private final int verticalSize;
    private final int horizontalSize;

    public Field(int verticalSize, int horizontalSize) {
        this.verticalSize = verticalSize;
        this.horizontalSize = horizontalSize;
        field = new char[verticalSize][horizontalSize];
        for (char[] row : field) {
            Arrays.fill(row, 'e');
        }
    }

    public int getVerticalSize() {
        return verticalSize;
    }


    public int getHorizontalSize() {
        return horizontalSize;
    }

    public void setChip(int x, int y, ChipState state) {
        if (state == ChipState.EMPTY) {
            field[x][y] = 'e';
        }
        if (state == ChipState.RED) {
            field[x][y] = 'r';
        }
        if (state == ChipState.BLUE) {
            field[x][y] = 'b';
        }
    }

    public void dropChip(int column, ChipState toDrop) {
        for (int i = this.getVerticalSize() - 1; i >= 0; i--) {
            if (this.getFieldPointState(i, column) == ChipState.EMPTY) {
                this.setChip(i, column, toDrop);
                return;
            }
        }
    }

    public boolean anyMoreMovesPossible() {
        for (int i = 0; i < this.getHorizontalSize(); i++) {
            if (dropPossible(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean dropPossible(int column) {
        for (int i = this.getVerticalSize() - 1; i >= 0; i--) {
            if (this.getFieldPointState(i, column) == ChipState.EMPTY) {
                return true;
            }
        }
        return false;
    }

    public ChipState getFieldPointState(int x, int y) {
        if (getFieldPoint(x, y) == 'e') {
            return ChipState.EMPTY;
        }
        if (getFieldPoint(x, y) == 'r') {
            return ChipState.RED;
        }
        if (getFieldPoint(x, y) == 'b') {
            return ChipState.BLUE;
        }
        return ChipState.EMPTY;
    }

    public char getFieldPoint(int x, int y) {
        return field[x][y];
    }

    public void printField() {
        for (int i = 0; i < field[0].length; i++) {
            System.out.print("|_" + (i + 1) + "_");
        }
        System.out.print("|");
        System.out.println();
        for (int i = 0; i <this.getVerticalSize(); i++) {
            for (int j = 0; j < this.getHorizontalSize(); j++) {
                if (field[i][j] == 'e') {
                    System.out.print("|___");
                }
                if (field[i][j] == 'r') {
                    System.out.print("|_X_");
                }
                if (field[i][j] == 'b') {
                    System.out.print("|_O_");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println();


    }
}
