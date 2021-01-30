package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    @Test
    void createEmptyBoard() {
        Board board = new Board(3);
        assertFalse(board.getStoneAt(Position.at(3,2)).isWhite());
    }

    @Test
    void countLiveStones() {
        Board board = new Board(5);
        assertEquals(0, board.countLiveStones(Colour.BLACK));
    }

    @Test
    void makeStoneWhite() {
        Board board = new Board(5);
        board.updateStoneAt(Position.at(3, 2), Colour.WHITE);
        assertTrue(board.getStoneAt(Position.at(3,2)).isWhite());
    }

    @Test
    void adjacentPositionsAllOccupied(){
        Board board = new Board(3);
        board.updateStoneAt(Position.at(1, 2), Colour.WHITE);
        board.updateStoneAt(Position.at(2, 1), Colour.BLACK);
        board.updateStoneAt(Position.at(2, 2), Colour.WHITE);
        assertTrue(board.areAdjacentPositionOccupied(Position.at(1,1)));
    }

    @Test
    void adjacentPositionsNotOccupied(){
        Board board = new Board(3);
        board.updateStoneAt(Position.at(1, 2), Colour.WHITE);
        board.updateStoneAt(Position.at(2, 1), Colour.BLACK);
        board.updateStoneAt(Position.at(2, 2), Colour.WHITE);
        assertFalse(board.areAdjacentPositionOccupied(Position.at(1,3)));
    }

    @Test
    void horizontalLiveStones(){
        Board board = new Board(5);
        board.updateStoneAt(Position.at(1, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(2, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(3, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(4, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(5, 1), Colour.BLACK);
        board.checkAllDirections();
        assertEquals(4, board.countLiveStones(Colour.WHITE));
    }

    @Test
    void fiveStonesInARowAreNotLive(){
        Board board = new Board(5);
        board.updateStoneAt(Position.at(1, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(2, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(3, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(4, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(5, 1), Colour.WHITE);
        board.checkAllDirections();
        assertEquals(0, board.countLiveStones(Colour.WHITE));
    }

    @Test
    void sixStonesInARowAreNotLive(){
        Board board = new Board(6);
        board.updateStoneAt(Position.at(1, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(2, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(3, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(4, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(5, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(6, 1), Colour.WHITE);
        board.checkAllDirections();
        assertEquals(0, board.countLiveStones(Colour.WHITE));
    }

    @Test
    void StonesInARowAreNotLive(){
        Board board = new Board(5);
        board.updateStoneAt(Position.at(1, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(2, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(3, 1), Colour.BLACK);
        board.updateStoneAt(Position.at(4, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(5, 1), Colour.WHITE);
        board.checkAllDirections();
        assertEquals(0, board.countLiveStones(Colour.WHITE));
    }

    @Test
    void verticalLiveStones(){
        Board board = new Board(5);
        board.updateStoneAt(Position.at(1, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(1, 2), Colour.WHITE);
        board.updateStoneAt(Position.at(1, 3), Colour.WHITE);
        board.updateStoneAt(Position.at(1, 4), Colour.WHITE);
        board.updateStoneAt(Position.at(1, 5), Colour.BLACK);
        board.checkAllDirections();
        assertEquals(4, board.countLiveStones(Colour.WHITE));
    }

    @Test
    void diagonalLiveStones(){
        Board board = new Board(5);
        board.updateStoneAt(Position.at(1, 1), Colour.WHITE);
        board.updateStoneAt(Position.at(2, 2), Colour.WHITE);
        board.updateStoneAt(Position.at(3, 3), Colour.WHITE);
        board.updateStoneAt(Position.at(4, 4), Colour.WHITE);
        board.updateStoneAt(Position.at(5, 5), Colour.BLACK);
        board.checkAllDirections();
        assertEquals(4, board.countLiveStones(Colour.WHITE));
    }

    @Test
    void LiveStones(){
        int size = 4;
        Board board = new Board(size);
        for(int x = 1; x <= size; ++x) {
            for(int y = 1; y <= size; ++y) {
                if(x*y % 2 == 0) {
                    board.updateStoneAt(Position.at(x, y), Colour.WHITE);
                } else {
                    board.updateStoneAt(Position.at(x, y), Colour.BLACK);
                }
            }
        }
        board.checkAllDirections();
        assertAll(
            () -> assertEquals(12, board.countLiveStones(Colour.WHITE)),
            () -> assertEquals(0, board.countLiveStones(Colour.BLACK))
        );
    }

    @Test
    void LiveStonesPart2(){
        int size = 4;
        Board board = new Board(size);
        for(int x = 1; x <= size; ++x) {
            for(int y = 1; y <= size; ++y) {
                if((x+y) % 2 == 0) {
                    board.updateStoneAt(Position.at(x, y), Colour.WHITE);
                } else {
                    board.updateStoneAt(Position.at(x, y), Colour.BLACK);
                }
            }
        }
        board.checkAllDirections();
        assertAll(
            () -> assertEquals(4, board.countLiveStones(Colour.WHITE)),
            () -> assertEquals(4, board.countLiveStones(Colour.BLACK))
        );
    }


}
