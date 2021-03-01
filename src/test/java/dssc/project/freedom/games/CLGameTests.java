package dssc.project.freedom.games;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;
import dssc.project.freedom.utilities.Utility;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.lineSeparator;

public class CLGameTests {

    private final String whiteTurn = "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator();
    private final String blackTurn = "Black it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator();
    private final String wholeNormalTurn = whiteTurn + blackTurn;


    @Test
    void testWhiteWinner() {
        GameRunner game = new GameRunner(4);
        game.parseWinner();
        game.testOutput("White won with " + 12 + " live stones against Black's " + 0 + lineSeparator());
    }

    @Test
    void testDraw() {
        GameRunner game = new GameRunner(4);
        game.parseDraw();
        String drawPrint = "Draw: both players have the same number of live stones: ";
        game.testOutput(drawPrint + 4 + lineSeparator());
    }

    @Test
    void testPlay() {
        GameRunner game = new GameRunner(1);
        game.parsePlay("1 1");
        game.testOutput(whiteTurn);
    }

    @Test
    void testPlayWithInvalidInteger() {
        GameRunner game = new GameRunner(1);
        String input = "q 1" + System.lineSeparator() + "1";
        game.parsePlay(input);
        game.testOutput( whiteTurn +
                "You didn't enter an integer! Enter again an integer" + lineSeparator());
    }

    @Test
    void testPlayWithPositionOutsideBoard() {
        GameRunner game = new GameRunner(1);
        String input = "1 0" + System.lineSeparator() + "1 1";
        game.parsePlay(input);
        game.testOutput( whiteTurn + "The position is not inside the board!"
                + " Enter the x and y coordinates of the stone:" + lineSeparator());
    }

    @Test
    void testPlayWithPositionAlreadyOccupied() {
        GameRunner game = new GameRunner(2);
        String input = "1 1" + lineSeparator() + "1 1" + lineSeparator() +
                "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2";
        game.parsePlay(input);
        String output = wholeNormalTurn +
                "The position is already occupied!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                wholeNormalTurn;
        game.testOutput(output);
    }

    @Test
    void testPlayWithNotAdjacentPosition() {
        GameRunner game = new GameRunner(3);
        String input = "1 1" + lineSeparator() + "1 3" + lineSeparator() +
                "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2" + lineSeparator() + " 1 3" + lineSeparator() +
                "2 3" + lineSeparator() + "3 3" + lineSeparator() + "3 2" + lineSeparator() + "3 1";
        game.parsePlay(input);
        String output = wholeNormalTurn +
                "The position is not adjacent to the previous one!" + " Enter the x and y coordinates of the stone:" +
                lineSeparator() + wholeNormalTurn.repeat(3) + whiteTurn;
        game.testOutput(output);
    }

    @Test
    void testPlayWithFreedomChoice() {
        GameRunner game = new GameRunner(3);
        String input = "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2" + lineSeparator() +
                " 1 1" + lineSeparator() + "1 3" + lineSeparator() + "2 3" + lineSeparator() +
                "3 3" + lineSeparator() + "3 2" + lineSeparator() + "3 1";
        game.parsePlay(input);
        String output = wholeNormalTurn.repeat(4) + whiteTurn;
        game.testOutput(output);
    }

    @Test
    void testLastMoveChoice() {
        int boardSize = 5;
        GameRunner game = new GameRunner(boardSize);
        String input = buildInputMoves(boardSize);
        game.parsePlay(input);
        String output = wholeNormalTurn.repeat(12) + whiteTurn +
                "Do you want to do the last move? (0 = no, 1 = yes)" + lineSeparator();
        game.testOutput(output);
    }

    private String buildInputMoves(int boardSize) {
        String input = "";
        for (int i = 1; i <= boardSize; ++i) {
            if (i % 2 == 1) {
                for (int j = 1; j <= boardSize; ++j)
                    input += i + " " + j + lineSeparator();
            } else {
                for (int j = boardSize; j >= 1; --j)
                    input += i + " " + j + lineSeparator();
            }
        }
        return input + "0";
    }

    @Test
    void testPrintBoard() {
        GameRunner game = new GameRunner(4);
        String white = Utility.getWhite();
        String black = Utility.getBlack();
        List<Position> positions = Arrays.asList(Position.at(1, 1), Position.at(2, 1));
        List<Colour> colours = Arrays.asList(Colour.WHITE, Colour.BLACK);
        game.parseBoard(positions, colours);
        String line = "  " + "+---".repeat(4) + "+" + lineSeparator();
        String boardAfterSecondMove = line + "4 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line + "3 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line + "2 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line +
                        "1 " + "| " + white + " | " + black + " |  ".repeat(2) + " |" + lineSeparator() +
                        line + "    1   2   3   4  " + lineSeparator();
        game.testOutput(boardAfterSecondMove);
    }
}