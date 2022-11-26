package me.cedric.test;

import me.cedric.game.ChipState;
import me.cedric.game.Field;
import me.cedric.game.Game;
import me.cedric.game.GameState;
import me.cedric.player.GreedyAI;
import me.cedric.player.Player;
import me.cedric.player.RandomAI;
import org.junit.jupiter.api.Test;

public class GreedyAITest {

    Field sf;
    Player player1;
    Player player2;

    void setUp() {
        sf = new Field(6, 7);
        player1 = new GreedyAI("AIGarret", 'r', ChipState.RED);
        player2 = new RandomAI("AIMortimer", 'b', ChipState.BLUE);
    }

    @Test
    public void TestRandomGame() {
        setUp();
        Game game = new Game(player1, player2, 6, 7, 0, true);
        game.startGame();
    }

    @Test
    public void test1000Games() {
        int winplayer1 = 0;
        int winplayer2 = 0;
        int remis = 0;
        for (int i = 1; i <= 1000; i++) {
            setUp();
            Game game = new Game(player1, player2, 6, 7, 0, false);
            game.startGame();
            if (game.getGameState() == GameState.PLAYER1WINNER) {
                winplayer1++;
            } else if (game.getGameState() == GameState.PLAYER2WINNER) {
                winplayer2++;
            } else if (game.getGameState() == GameState.REMIS) {
                remis++;
            }

        }
        System.out.println("Player1Wins: " + winplayer1 + " " + ((winplayer1 * 100 / (double) (winplayer1 + winplayer2 + remis)) + "%"));
        System.out.println("Player2Wins: " + winplayer1 + " " + ((winplayer2 * 100 / (double) (winplayer1 + winplayer2 + remis)) + "%"));
        System.out.println("Remis: " + remis + " " + ((remis * 100 / (double) (winplayer1 + winplayer2 + remis)) + "%"));
    }
}
