package dssc.project.freedom.players;

import dssc.project.freedom.Board;
import dssc.project.freedom.CommandLineGame;
import org.junit.jupiter.api.Test;

import static dssc.project.freedom.Colour.BLACK;
import static dssc.project.freedom.Colour.WHITE;
import static dssc.project.freedom.Position.at;
import static dssc.project.freedom.CommandLineGame.*;
import static org.junit.Assert.assertEquals;

public class GreedyPlayerTests {

    @Test
    public void chooseFourthStone() {
        int boardSize = 5;
        Board board = new Board(boardSize);
        for (int i = 1; i <= 3; ++i) {
            for (int j = 1; j <= 3; ++j) {
                if (i == j) {
                    board.updateStoneAt(at(i, j), WHITE);
                    board.updateStoneAt(at(i, j + 1), BLACK);
                }
            }
        }
        GreedyPlayer.setBoard(board);
        GreedyPlayer.setPrevious(at(3, 4));
        GreedyPlayer greedyPlayer = new GreedyPlayer("White", WHITE);
        assertEquals(at(4, 4), greedyPlayer.getPlayerPosition());
    }
}
