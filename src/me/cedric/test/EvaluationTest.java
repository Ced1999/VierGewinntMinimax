package me.cedric.test;

import me.cedric.game.ChipState;
import me.cedric.game.EvaluationUtilities;
import me.cedric.game.Field;
import me.cedric.player.HumanPlayer;
import me.cedric.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EvaluationTest {
    Field sf;
    Player player1;
    Player player2;

    void setUp() {
        sf = new Field(6, 7);
        player1 = new HumanPlayer("Cedric", 'r', ChipState.RED);
        player2 = new HumanPlayer("Otto", 'b', ChipState.BLUE);
    }
    @Test
    void diagonalTest() {
        setUp();
        sf.setChip(0,0,ChipState.BLUE);
        sf.setChip(1,1,ChipState.BLUE);
        sf.setChip(2,2,ChipState.BLUE);
        sf.setChip(3,3,ChipState.BLUE);
        sf.setChip(4,4,ChipState.RED);
        sf.setChip(5,3,ChipState.RED);
        sf.setChip(3, 5, ChipState.RED);
        sf.setChip(2, 6, ChipState.RED);


        sf.printField();
        assertTrue(EvaluationUtilities.checkDiagonals(player2, sf));
        assertTrue(EvaluationUtilities.checkDiagonals(player1, sf));

    }

    @Test
    void horizontalTest() {
        setUp();
        sf.setChip(5, 4, ChipState.RED);
        sf.setChip(5, 3, ChipState.RED);
        sf.setChip(5, 2, ChipState.RED);
        sf.setChip(5, 1, ChipState.RED);
        sf.printField();
        assertTrue(EvaluationUtilities.checkHorizontal(player1, sf));
    }

    @Test
    void verticalTest() {
        setUp();
        sf.setChip(5, 4, ChipState.RED);
        sf.setChip(5, 3, ChipState.RED);
        sf.setChip(5, 2, ChipState.RED);
        sf.setChip(5, 1, ChipState.RED);
        sf.dropChip(4, ChipState.RED);
        sf.dropChip(4, ChipState.RED);
        sf.dropChip(4, ChipState.RED);
        sf.dropChip(6, ChipState.BLUE);
        sf.dropChip(6, ChipState.BLUE);
        sf.dropChip(6, ChipState.BLUE);
        sf.dropChip(6, ChipState.BLUE);
        sf.dropChip(6, ChipState.BLUE);
        sf.dropChip(6, ChipState.BLUE);
        sf.printField();
        assertTrue(EvaluationUtilities.checkVertical(player1, sf));
        assertTrue(EvaluationUtilities.checkVertical(player2, sf));

    }

    @Test
    void remisTest() {
        setUp();
        for (int row = 0; row < sf.getVerticalSize(); row++) {
            for (int column = 0; column < sf.getHorizontalSize(); column++) {
                sf.setChip(row, column, ChipState.RED);
            }
        }
        sf.printField();
        assertFalse(sf.anyMoreMovesPossible(),"There are Still Moves Possible Error");
        sf.setChip(sf.getVerticalSize() - 1, sf.getHorizontalSize() - 1, ChipState.EMPTY);
        sf.printField();
        assertTrue(sf.anyMoreMovesPossible(), "No moves Possible Error");
    }
    @Test
    void possibleMovesTest() {
        setUp();
        sf.dropChip(0,ChipState.RED);
        sf.dropChip(0,ChipState.BLUE);
        sf.dropChip(0,ChipState.RED);
        sf.dropChip(0,ChipState.BLUE);
        sf.dropChip(0,ChipState.RED);
        sf.printField();
        System.out.println(sf.getValidLocations());
    }

}
