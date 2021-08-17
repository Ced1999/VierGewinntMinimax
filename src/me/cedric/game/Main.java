package me.cedric.game;

import me.cedric.player.HumanPlayer;
import me.cedric.player.Player;

public class Main {
    public static void main(String[] args) {

        Field sf = new Field(6,7);
        sf.printField();
        sf.setChip(5,4,ChipState.RED);
        sf.setChip(5,3,ChipState.RED);
        sf.setChip(5,2,ChipState.RED);
        sf.setChip(5,1,ChipState.RED);
        sf.setChip(0,0,ChipState.BLUE);
        sf.setChip(1,1,ChipState.BLUE);
        sf.setChip(2,2,ChipState.BLUE);
        sf.setChip(3,3,ChipState.BLUE);

        sf.printField();
        Player player1 = new HumanPlayer("Cedric", 'r');
        Player player2 = new HumanPlayer("Otto", 'b');
        sf.dropChip(4,ChipState.RED);
        sf.dropChip(4,ChipState.RED);
        sf.dropChip(4,ChipState.RED);
        sf.printField();

        Game game = new Game(player1,player2,6,7);
        game.startGame();

        //System.out.println(EvaluationUtilities.checkVertical(player1,sf));
        //System.out.println(EvaluationUtilities.checkHorizontal(player1,sf));
        //System.out.println(EvaluationUtilities.checkDiagonals(player2,sf));
        //System.out.println(checkForWinner(player1,sf));
    }
}
