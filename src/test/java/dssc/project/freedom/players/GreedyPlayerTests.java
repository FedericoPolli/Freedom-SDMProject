package dssc.project.freedom.players;

import dssc.project.freedom.basis.Board;
import dssc.project.freedom.utilities.RandomInteger;
import org.junit.jupiter.api.Test;

import static dssc.project.freedom.basis.Colour.*;
import static dssc.project.freedom.basis.Position.at;
import static org.junit.jupiter.api.Assertions.*;

public class GreedyPlayerTests {

    private final GreedyPlayer greedyPlayer = new GreedyPlayer("White", WHITE, new RandomInteger());
    private final Board board = new Board(5);

    @Test
    void chooseFourthStone() {
        for (int i = 1; i <= 3; ++i) {
            for (int j = 1; j <= 3; ++j) {
                if (i == j) {
                    board.colourStoneAt(at(i, j), WHITE);
                    board.colourStoneAt(at(i, j + 1), BLACK);
                }
            }
        }
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(3, 4));
        assertEquals(at(4, 4), greedyPlayer.getPlayerPosition());
    }

    @Test
    void doNotChooseFifthStone() {
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                if (i == j) {
                    board.colourStoneAt(at(i, j), WHITE);
                    board.colourStoneAt(at(i, j + 1), BLACK);
                }
            }
        }
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(4, 5));
        assertNotEquals(at(5, 5), greedyPlayer.getPlayerPosition());
    }

    @Test
    void hasTheFreedomToChooseAStone() {
        board.colourStoneAt(at(1, 2), WHITE);
        board.colourStoneAt(at(2, 2), BLACK);
        board.colourStoneAt(at(2, 1), WHITE);
        board.colourStoneAt(at(1, 1), BLACK);
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(3, 4));
        assertTrue(board.getFreePositions().contains(greedyPlayer.getPlayerPosition()));
    }

    @Test
    void chooseStoneInLongestRow() {
        board.colourStoneAt(at(1, 2), WHITE);
        board.colourStoneAt(at(2, 2), BLACK);
        board.colourStoneAt(at(2, 3), WHITE);
        board.colourStoneAt(at(3, 3), BLACK);
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(3, 3));
        assertEquals(at(3, 4), greedyPlayer.getPlayerPosition());
    }

    @Test
    void hasToPutFifthStone() {
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                if (i == j) {
                    board.colourStoneAt(at(i, j), WHITE);
                    board.colourStoneAt(at(i, j + 1), BLACK);
                }
            }
        }
        board.colourStoneAt(at(5, 4), BLACK);
        board.colourStoneAt(at(3, 5), WHITE);
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(4, 5));
        assertEquals(at(5, 5), greedyPlayer.getPlayerPosition());
    }

    @Test
    void blocksRowOf4OfOppositePlayer() {
        for (int i = 1; i <= 2; ++i) {
            for (int j = 1; j <= 2; ++j) {
                if (i == j) {
                    board.colourStoneAt(at(i, j), WHITE);
                    board.colourStoneAt(at(i, j + 1), BLACK);
                }
            }
        }
        board.colourStoneAt(at(3, 4), BLACK);
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(3, 4));
        assertEquals(at(4, 5), greedyPlayer.getPlayerPosition());
    }
}