package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static dssc.project.freedom.Colour.BLACK;
import static dssc.project.freedom.Colour.WHITE;
import static dssc.project.freedom.Position.at;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    @Test
    void createEmptyBoard() {
        Board board = new Board(3);
        assertFalse(board.getStoneAt(at(3,2)).isOfColour(WHITE));
    }

    @Test
    void countLiveStones() {
        Board board = new Board(5);
        assertEquals(0, board.countLiveStones(BLACK));
    }

    @Test
    void makeStoneWhite() {
        Board board = new Board(5);
        board.updateStoneAt(at(3, 2), WHITE);
        assertTrue(board.getStoneAt(at(3,2)).isOfColour(WHITE));
    }

    @Test
    void adjacentPositionsAllOccupied(){
        Board board = new Board(3);
        board.updateStoneAt(at(1, 2), WHITE);
        board.updateStoneAt(at(2, 1), BLACK);
        board.updateStoneAt(at(2, 2), WHITE);
        assertTrue(board.areAdjacentPositionOccupied(at(1,1)));
    }

    @Test
    void adjacentPositionsNotOccupied(){
        Board board = new Board(3);
        board.updateStoneAt(at(1, 2), WHITE);
        board.updateStoneAt(at(2, 1), BLACK);
        board.updateStoneAt(at(2, 2), WHITE);
        assertFalse(board.areAdjacentPositionOccupied(at(1,3)));
    }

    @Test
    void horizontalLiveStones(){
        Board board = new Board(5);
        board.updateStoneAt(at(1, 1), WHITE);
        board.updateStoneAt(at(2, 1), WHITE);
        board.updateStoneAt(at(3, 1), WHITE);
        board.updateStoneAt(at(4, 1), WHITE);
        board.updateStoneAt(at(5, 1), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(4, board.countLiveStones(WHITE));
    }

    @Test
    void fiveStonesInARowAreNotLive(){
        Board board = new Board(5);
        board.updateStoneAt(at(1, 1), WHITE);
        board.updateStoneAt(at(2, 1), WHITE);
        board.updateStoneAt(at(3, 1), WHITE);
        board.updateStoneAt(at(4, 1), WHITE);
        board.updateStoneAt(at(5, 1), WHITE);
        board.checkBoardAndMakeStonesLive();
        assertEquals(0, board.countLiveStones(WHITE));
    }

    @Test
    void sixStonesInARowAreNotLive(){
        Board board = new Board(6);
        board.updateStoneAt(at(1, 1), WHITE);
        board.updateStoneAt(at(2, 1), WHITE);
        board.updateStoneAt(at(3, 1), WHITE);
        board.updateStoneAt(at(4, 1), WHITE);
        board.updateStoneAt(at(5, 1), WHITE);
        board.updateStoneAt(at(6, 1), WHITE);
        board.checkBoardAndMakeStonesLive();
        assertEquals(0, board.countLiveStones(WHITE));
    }

    @Test
    void StonesInARowAreNotLive(){
        Board board = new Board(5);
        board.updateStoneAt(at(1, 1), WHITE);
        board.updateStoneAt(at(2, 1), WHITE);
        board.updateStoneAt(at(3, 1), BLACK);
        board.updateStoneAt(at(4, 1), WHITE);
        board.updateStoneAt(at(5, 1), WHITE);
        board.checkBoardAndMakeStonesLive();
        assertEquals(0, board.countLiveStones(WHITE));
    }

    @Test
    void verticalLiveStones(){
        Board board = new Board(5);
        board.updateStoneAt(at(1, 1), WHITE);
        board.updateStoneAt(at(1, 2), WHITE);
        board.updateStoneAt(at(1, 3), WHITE);
        board.updateStoneAt(at(1, 4), WHITE);
        board.updateStoneAt(at(1, 5), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(4, board.countLiveStones(WHITE));
    }

    @Test
    void diagonalLiveStones(){
        Board board = new Board(5);
        board.updateStoneAt(at(1, 1), WHITE);
        board.updateStoneAt(at(2, 2), WHITE);
        board.updateStoneAt(at(3, 3), WHITE);
        board.updateStoneAt(at(4, 4), WHITE);
        board.updateStoneAt(at(5, 5), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(4, board.countLiveStones(WHITE));
    }

    @Test
    void LiveStones(){
        int size = 4;
        Board board = new Board(size);
        for(int x = 1; x <= size; ++x) {
            for(int y = 1; y <= size; ++y) {
                if(x*y % 2 == 0) {
                    board.updateStoneAt(at(x, y), WHITE);
                } else {
                    board.updateStoneAt(at(x, y), BLACK);
                }
            }
        }
        board.checkBoardAndMakeStonesLive();
        assertAll(
            () -> assertEquals(12, board.countLiveStones(WHITE)),
            () -> assertEquals(0, board.countLiveStones(BLACK))
        );
    }

    @Test
    void LiveStonesPart2(){
        int size = 4;
        Board board = new Board(size);
        for(int x = 1; x <= size; ++x) {
            for(int y = 1; y <= size; ++y) {
                if((x+y) % 2 == 0) {
                    board.updateStoneAt(at(x, y), WHITE);
                } else {
                    board.updateStoneAt(at(x, y), BLACK);
                }
            }
        }
        board.checkBoardAndMakeStonesLive();
        assertAll(
            () -> assertEquals(4, board.countLiveStones(WHITE)),
            () -> assertEquals(4, board.countLiveStones(BLACK))
        );
    }

    @Test
    void testPrintBoard(){
        Board board = new Board(3);
        board.printBoard();
    }

}
