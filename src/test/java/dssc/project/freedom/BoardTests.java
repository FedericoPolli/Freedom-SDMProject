package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    @Test
    void createEmptyBoard() {
        Board board = new Board(3);
        assertFalse(board.getStoneAt(Position.createAt(3,2)).isWhite());
    }

    @Test
    void countLiveStones() {
        Board board = new Board(5);
        assertEquals(0, board.countLiveStones());
    }

    @Test
    void makeStoneWhite() {
        Board board = new Board(5);
        board.updateStoneAt(Position.createAt(3, 2), Colour.WHITE);
        assertTrue(board.getStoneAt(Position.createAt(3,2)).isWhite());
    }

}
