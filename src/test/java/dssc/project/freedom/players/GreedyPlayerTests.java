package dssc.project.freedom.players;

import dssc.project.freedom.RandomInteger;
import dssc.project.freedom.basis.Board;
import org.junit.jupiter.api.Test;

import static dssc.project.freedom.basis.Colour.*;
import static dssc.project.freedom.basis.Position.at;
import static org.junit.jupiter.api.Assertions.*;

public class GreedyPlayerTests {

    GreedyPlayer greedyPlayer = new GreedyPlayer("White", WHITE, new RandomInteger());

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
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(3, 4));
        assertEquals(at(4, 4), greedyPlayer.getPlayerPosition());
    }

    @Test
    public void doNotChooseFifthStone() {
        int boardSize = 5;
        Board board = new Board(boardSize);
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                if (i == j) {
                    board.updateStoneAt(at(i, j), WHITE);
                    board.updateStoneAt(at(i, j + 1), BLACK);
                }
            }
        }
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(4, 5));
        assertNotEquals(at(5, 5), greedyPlayer.getPlayerPosition());
    }

    @Test
    public void hasTheFreedomToChooseAStone() {
        int boardSize = 5;
        Board board = new Board(boardSize);
        board.updateStoneAt(at(1, 2), WHITE);
        board.updateStoneAt(at(2, 2), BLACK);
        board.updateStoneAt(at(2, 1), WHITE);
        board.updateStoneAt(at(1, 1), BLACK);
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(3, 4));
        assertTrue(board.getFreePositions().contains(greedyPlayer.getPlayerPosition()));
    }

    @Test
    public void chooseStoneInLongestRow() {
        int boardSize = 5;
        Board board = new Board(boardSize);
        board.updateStoneAt(at(1, 2), WHITE);
        board.updateStoneAt(at(2, 2), BLACK);
        board.updateStoneAt(at(2, 3), WHITE);
        board.updateStoneAt(at(3, 3), BLACK);
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(3, 3));
        assertEquals(at(3, 4), greedyPlayer.getPlayerPosition());
    }

    @Test
    public void hasToPutFifthStone() {
        int boardSize = 5;
        Board board = new Board(boardSize);
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                if (i == j) {
                    board.updateStoneAt(at(i, j), WHITE);
                    board.updateStoneAt(at(i, j + 1), BLACK);
                }
            }
        }
        board.updateStoneAt(at(5, 4), BLACK);
        board.updateStoneAt(at(3, 5), WHITE);
        GreedyPlayer.updateBoardAndPreviousPosition(board, at(4, 5));
        assertEquals(at(5, 5), greedyPlayer.getPlayerPosition());
    }
}