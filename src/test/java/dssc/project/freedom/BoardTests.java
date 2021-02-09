package dssc.project.freedom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static dssc.project.freedom.Colour.*;
import static dssc.project.freedom.Position.at;
import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    @Test
    void createEmptyBoard() {
        Board board = new Board(3);
        assertFalse(board.stoneIsAlreadyPlacedAt(at(3,2)));
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
        assertTrue(board.stoneIsAlreadyPlacedAt(at(3,2)));
    }

    @ParameterizedTest
    @MethodSource("positionToBeChecked")
    void areAdjacentPositionsOccupied(Board board, int x, int y, boolean expected){
        assertEquals(expected, board.areAdjacentPositionOccupied(at(x,y)));
    }

    private static Stream<Arguments> positionToBeChecked() {
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

    @ParameterizedTest
    @ValueSource(ints = {5, 6})
    void moreThanFiveStonesOfSameColourInARowAreNotLive(int row){
        Board board = new Board(row);
        for (int i = 1; i <= row; ++i) {
            board.updateStoneAt(at(i, 1), WHITE);
        }
        board.checkBoardAndMakeStonesLive();
        assertEquals(0, board.countLiveStones(WHITE));
    }

    @Test
    void notEnoughStonesOfSameColourToBeLive(){
        Board board = new Board(5);
        for (int i = 1; i <= 5; ++i) {
            board.updateStoneAt(at(i, 1), i == 3 ? WHITE : BLACK);
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
                board1.updateStoneAt(at(x, y), (x * y) % 2 == 0 ? WHITE : BLACK);
                board2.updateStoneAt(at(x, y), (x + y) % 2 == 0 ? WHITE : BLACK);
            }
        }
        return Stream.of(
                Arguments.of(board1, 12, 0),
                Arguments.of(board2, 4, 4)
        );
    }

    @Test
    public void testPrintBoard(){
        ApplicationRunner application = new ApplicationRunner();
        application.parseBoard(Position.at(1, 1), Colour.WHITE);
        application.parseBoard(Position.at(2, 1), Colour.BLACK);
        String boardAfterFirstMove = "+---".repeat(4) + "+" + lineSeparator() +
                "|   ".repeat(4) + "|" + lineSeparator() +
                "+---".repeat(4) + "+" + lineSeparator() +
                "|   ".repeat(4) + "|" + lineSeparator() +
                "+---".repeat(4) + "+" + lineSeparator() +
                "|   ".repeat(4) + "|" + lineSeparator() +
                "+---".repeat(4) + "+" + lineSeparator() +
                "| " + '\u26AA' + " |  ".repeat(3) + " |" + lineSeparator() +
                "+---".repeat(4) + "+" + lineSeparator();
        String boardAfterSecondMove = "+---".repeat(4) + "+" + lineSeparator() +
                "|   ".repeat(4) + "|" + lineSeparator() +
                "+---".repeat(4) + "+" + lineSeparator() +
                "|   ".repeat(4) + "|" + lineSeparator() +
                "+---".repeat(4) + "+" + lineSeparator() +
                "|   ".repeat(4) + "|" + lineSeparator() +
                "+---".repeat(4) + "+" + lineSeparator() +
                "| " + '\u26AA' + " | " +'\u26AB' + " |  ".repeat(2) + " |" + lineSeparator() +
                "+---".repeat(4) + "+" + lineSeparator();
        application.showsMove(boardAfterFirstMove + boardAfterSecondMove);
    }
}
