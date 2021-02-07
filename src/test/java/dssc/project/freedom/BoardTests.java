package dssc.project.freedom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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
    void countLiveStonesInEmptyBoard() {
        Board board = new Board(5);
        assertEquals(0, board.countLiveStones(BLACK));
    }

    @Test
    void changeStoneColour() {
        Board board = new Board(5);
        board.updateStoneAt(at(3, 2), WHITE);
        assertTrue(board.getStoneAt(at(3,2)).isOfColour(WHITE));
    }

    @ParameterizedTest
    @MethodSource("PositionToBeChecked")
    void areAdjacentPositionsOccupied(Board board, int x, int y, boolean expected){
        assertEquals(expected, board.areAdjacentPositionOccupied(at(x,y)));
    }

    private static Stream<Arguments> PositionToBeChecked() {
        Board board = new Board(3);
        board.updateStoneAt(at(1, 2), WHITE);
        board.updateStoneAt(at(2, 1), BLACK);
        board.updateStoneAt(at(2, 2), WHITE);
        return Stream.of(
                Arguments.of(board, 1, 1, true),
                Arguments.of(board, 1, 3, false)
        );
    }

    @Test
    void checkLiveStonesInHorizontalRow(){
        Board board = new Board(5);
        for (int i = 1; i <= 4; ++i) {
            board.updateStoneAt(at(i, 1), WHITE);
        }
        board.updateStoneAt(at(5, 1), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(4, board.countLiveStones(WHITE));
    }

    @Test
    void checkLiveStonesInVerticalRow(){
        Board board = new Board(5);
        for (int i = 1; i <= 4; ++i) {
            board.updateStoneAt(at(1, i), WHITE);
        }
        board.updateStoneAt(at(1, 5), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(4, board.countLiveStones(WHITE));
    }

    @Test
    void checkLiveStonesInDiagonalRow(){
        Board board = new Board(5);
        for (int i = 1; i <= 4; ++i) {
            board.updateStoneAt(at(i, i), WHITE);
        }
        board.updateStoneAt(at(5, 5), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(4, board.countLiveStones(WHITE));
    }

    @Test
    void fiveStonesInARowAreNotLive(){
        Board board = new Board(5);
        for (int i = 1; i <= 5; ++i) {
            board.updateStoneAt(at(i, 1), WHITE);
        }
        board.checkBoardAndMakeStonesLive();
        assertEquals(0, board.countLiveStones(WHITE));
    }

    @Test
    void sixStonesOfSameColourInARowAreNotLive(){
        Board board = new Board(6);
        for (int i = 1; i <= 6; ++i) {
            board.updateStoneAt(at(i, 1), WHITE);
        }
        board.checkBoardAndMakeStonesLive();
        assertEquals(0, board.countLiveStones(WHITE));
    }

    @Test
    void notEnoughStonesOfSameColourToBeLive(){
        Board board = new Board(5);
        for (int i = 1; i <= 5; ++i) {
            if (i != 3)
                board.updateStoneAt(at(i, 1), WHITE);
        }
        board.updateStoneAt(at(3, 1), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(0, board.countLiveStones(WHITE));
    }

    @Test
    void CountLiveStones(){
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
    void CountLiveStonesPart2(){
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
