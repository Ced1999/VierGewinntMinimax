package me.cedric.test;

import me.cedric.game.ChipState;
import me.cedric.game.Field;
import me.cedric.game.Game;
import me.cedric.player.GreedyAI;
import me.cedric.player.MiniMaxAI;
import me.cedric.player.Player;
import me.cedric.player.RandomAI;
import org.junit.jupiter.api.Test;

public class MiniMaxAITest {
    Field sf;
    Player player1;
    Player player2;

    void setUp() {
        sf = new Field(6, 7);
        player1 = new GreedyAI("AIGreedy", 'r', ChipState.RED);
        player2 = new MiniMaxAI("AIMiniMax", 'b', ChipState.BLUE);
    }

    @Test
    public void TestRandomGame() {
        setUp();
        Game game = new Game(player1, player2, 6, 7, 0, true);
        game.startGame();
    }
}
