package me.cedric.game;

import me.cedric.player.Player;

public class EvaluationUtilities {
    public static boolean checkHorizontal(Player player, Field gameField) {
        for (int i = 0; i < gameField.getVerticalSize(); i++) {
            for (int j = 0; j < gameField.getHorizontalSize() - 3; j++) {
                if (gameField.getFieldPoint(i, j) == player.getToken() &&
                        gameField.getFieldPoint(i, j + 1) == player.getToken() &&
                        gameField.getFieldPoint(i, j + 2) == player.getToken() &&
                        gameField.getFieldPoint(i, j + 3) == player.getToken()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkVertical(Player player, Field gameField) {
        for (int i = 0; i < gameField.getVerticalSize() - 3; i++) {
            for (int j = 0; j < gameField.getHorizontalSize(); j++) {
                if (gameField.getFieldPoint(i, j) == player.getToken() &&
                        gameField.getFieldPoint(i + 1, j) == player.getToken() &&
                        gameField.getFieldPoint(i + 2, j) == player.getToken() &&
                        gameField.getFieldPoint(i + 3, j) == player.getToken()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkDiagonals(Player player, Field gameField) {
        // ascendingDiagonalCheck 
        for (int column = 3; column < gameField.getHorizontalSize(); column++) {
            for (int row = 0; row < gameField.getVerticalSize() - 3; row++) {
                if (gameField.getFieldPoint(row, column) == player.getToken() && gameField.getFieldPoint(row + 1, column - 1) == player.getToken() && gameField.getFieldPoint(row + 2, column - 2) == player.getToken() && gameField.getFieldPoint(row + 3, column - 3) == player.getToken())
                    return true;
            }
        }
        // descendingDiagonalCheck
        for (int column = 3; column < gameField.getHorizontalSize(); column++) {
            for (int row = 3; row < gameField.getVerticalSize(); row++) {
                if (gameField.getFieldPoint(row, column) == player.getToken() && gameField.getFieldPoint(row - 1, column - 1) == player.getToken() && gameField.getFieldPoint(row - 2, column - 2) == player.getToken() && gameField.getFieldPoint(row - 3, column - 3) == player.getToken())
                    return true;
            }
        }
        return false;
    }

    public static boolean checkForWinner(Player player, Field gameField) {
        return checkHorizontal(player, gameField) || checkVertical(player, gameField) || checkDiagonals(player, gameField);
    }


}
