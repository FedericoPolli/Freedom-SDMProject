package dssc.project.freedom.basis;

import dssc.project.freedom.utilities.Utility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static dssc.project.freedom.basis.Colour.*;
import static dssc.project.freedom.basis.Position.at;
import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    @Test
    void createEmptyBoard() {
        Board board = new Board(2);
        assertAll(
                () -> assertFalse(board.stoneIsAlreadyPlacedAt(at(1,1))),
                () -> assertFalse(board.stoneIsAlreadyPlacedAt(at(1,2))),
                () -> assertFalse(board.stoneIsAlreadyPlacedAt(at(2,1))),
                () -> assertFalse(board.stoneIsAlreadyPlacedAt(at(2,2)))
        );
    }

    @Test
    void testGetBoardSize(){
        Board board = new Board(5);
        assertEquals(board.getBoardSize(), 5);
    }

    @Test
    void countLiveStonesInEmptyBoard() {
        Board board = new Board(5);
        assertEquals(0, board.countLiveStones(BLACK));
    }

    @Test
    void changeStoneColour() {
        Board board = new Board(5);
        board.colourStoneAt(at(3, 2), WHITE);
        assertTrue(board.stoneIsAlreadyPlacedAt(at(3,2)));
    }

    @ParameterizedTest
    @MethodSource("positionToBeChecked")
    void testAreAdjacentPositionsFree(Board board, int x, int y, boolean expected){
        assertEquals(expected, board.areAdjacentPositionFree(at(x,y)));
    }

    private static Stream<Arguments> positionToBeChecked() {
        Board board = new Board(3);
        board.colourStoneAt(at(1, 2), WHITE);
        board.colourStoneAt(at(2, 1), BLACK);
        board.colourStoneAt(at(2, 2), WHITE);
        return Stream.of(
                Arguments.of(board, 1, 1, false),
                Arguments.of(board, 1, 3, true)
        );
    }

    @ParameterizedTest
    @CsvSource({"1, 1, true", "4, 4, false"})
    void positionInsideBoard(int x, int y, boolean expected){
        Board board = new Board(3);
        assertEquals(expected, board.positionIsInsideTheBoard(at(x,y)));
    }

    @Test
    void checkLiveStonesInHorizontalRow(){
        Board board = new Board(5);
        for (int i = 1; i <= 4; ++i) {
            board.colourStoneAt(at(i, 1), WHITE);
        }
        board.colourStoneAt(at(5, 1), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(4, board.countLiveStones(WHITE));
    }

    @Test
    void checkLiveStonesInVerticalRow(){
        Board board = new Board(5);
        for (int i = 1; i <= 4; ++i) {
            board.colourStoneAt(at(1, i), WHITE);
        }
        board.colourStoneAt(at(1, 5), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(4, board.countLiveStones(WHITE));
    }

    @Test
    void checkLiveStonesInDiagonalRow(){
        Board board = new Board(5);
        for (int i = 1; i <= 4; ++i) {
            board.colourStoneAt(at(i, i), WHITE);
        }
        board.colourStoneAt(at(5, 5), BLACK);
        board.checkBoardAndMakeStonesLive();
        assertEquals(4, board.countLiveStones(WHITE));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6})
    void moreThanFiveStonesOfSameColourInARowAreNotLive(int row){
        Board board = new Board(row);
        for (int i = 1; i <= row; ++i) {
            board.colourStoneAt(at(i, 1), WHITE);
        }
        board.checkBoardAndMakeStonesLive();
        assertEquals(0, board.countLiveStones(WHITE));
    }

    @Test
    void notEnoughStonesOfSameColourToBeLive(){
        Board board = new Board(5);
        for (int i = 1; i <= 5; ++i) {
            board.colourStoneAt(at(i, 1), i == 3 ? WHITE : BLACK);
        }
        board.checkBoardAndMakeStonesLive();
        assertEquals(0, board.countLiveStones(WHITE));
    }

    @ParameterizedTest
    @MethodSource("generateFullBoard")
    void CountLiveStones(Board board, int expectedWhite, int expectedBlack) {
        board.checkBoardAndMakeStonesLive();
        assertAll(
                () -> assertEquals(expectedWhite, board.countLiveStones(WHITE)),
                () -> assertEquals(expectedBlack, board.countLiveStones(BLACK))
        );
    }

    private static Stream<Arguments> generateFullBoard() {
        int size = 4;
        Board board1 = new Board(size);
        Board board2 = new Board(size);
        for(int x = 1; x <= size; ++x) {
            for(int y = 1; y <= size; ++y) {
                board1.colourStoneAt(at(x, y), (x * y) % 2 == 0 ? WHITE : BLACK);
                board2.colourStoneAt(at(x, y), (x + y) % 2 == 0 ? WHITE : BLACK);
            }
        }
        return Stream.of(
                Arguments.of(board1, 12, 0),
                Arguments.of(board2, 4, 4)
        );
    }

    @Test
    void testMaximumNumberOfStonesInARow() {
        Board board = new Board(5);
        board.colourStoneAt(at(1, 2), WHITE);
        board.colourStoneAt(at(2, 2), BLACK);
        board.colourStoneAt(at(2, 1), WHITE);
        board.colourStoneAt(at(1, 1), BLACK);
        assertEquals(2, board.getMaximumNumberOfStonesInARow(at(1, 1), WHITE));
    }

    @Test
    public void testBoardToString(){
        String white = Utility.getWhite();
        String black = Utility.getBlack();
        Board board = new Board(4);
        board.colourStoneAt(Position.at(1, 1), Colour.WHITE);
        board.colourStoneAt(Position.at(2, 1), Colour.BLACK);
        String line = "  " + "+---".repeat(4) + "+" + lineSeparator();
        String boardAfterSecondMove =
                line + "4 " + "|   ".repeat(4) + "|" + lineSeparator() +
                line + "3 " + "|   ".repeat(4) + "|" + lineSeparator() +
                line + "2 " + "|   ".repeat(4) + "|" + lineSeparator() +
                line +
                "1 " + "| " + white + " | " + black + " |  ".repeat(2) + " |" + lineSeparator() +
                line + "    1   2   3   4  " + lineSeparator();
        assertEquals(boardAfterSecondMove, board.toString());
    }
}