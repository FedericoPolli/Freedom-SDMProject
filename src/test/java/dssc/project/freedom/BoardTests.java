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
        assertEquals(0, board.countLiveStones(Colour.BLACK));
    }

    @Test
    void makeStoneWhite() {
        Board board = new Board(5);
        board.updateStoneAt(Position.createAt(3, 2), Colour.WHITE);
        assertTrue(board.getStoneAt(Position.createAt(3,2)).isWhite());
    }

    @Test
    void adjacentPositionsAllOccupied(){
        Board board = new Board(3);
        board.updateStoneAt(Position.createAt(1, 2), Colour.WHITE);
        board.updateStoneAt(Position.createAt(2, 1), Colour.BLACK);
        board.updateStoneAt(Position.createAt(2, 2), Colour.WHITE);
        assertTrue(board.areAdjacentPositionOccupied(Position.createAt(1,1)));
    }

    @Test
    void adjacentPositionsNotOccupied(){
        Board board = new Board(3);
        board.updateStoneAt(Position.createAt(1, 2), Colour.WHITE);
        board.updateStoneAt(Position.createAt(2, 1), Colour.BLACK);
        board.updateStoneAt(Position.createAt(2, 2), Colour.WHITE);
        assertFalse(board.areAdjacentPositionOccupied(Position.createAt(1,3)));
    }

    @Test
    void horizontalLiveStones(){
        Board board = new Board(5);
        board.updateStoneAt(Position.createAt(1, 1), Colour.WHITE);
        board.updateStoneAt(Position.createAt(2, 1), Colour.WHITE);
        board.updateStoneAt(Position.createAt(3, 1), Colour.WHITE);
        board.updateStoneAt(Position.createAt(4, 1), Colour.WHITE);
        board.updateStoneAt(Position.createAt(5, 1), Colour.BLACK);
        board.check4Horizontal();
        assertEquals(4, board.countLiveStones(Colour.WHITE));
    }

}
