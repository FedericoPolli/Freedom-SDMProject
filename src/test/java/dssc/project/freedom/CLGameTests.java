package dssc.project.freedom;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.lineSeparator;

public class CLGameTests {

    private final String white = Utility.getWhite();
    private final String black = Utility.getBlack();
    private final String initialBoardOfDim1 = "  +---+" + lineSeparator() +
            "1 |   |" + lineSeparator() +
            "  +---+" + lineSeparator() +
            "    1  " + lineSeparator();
    private final String finalBoardOfDim1 = "  +---+" + lineSeparator() +
            "1 | " + white + " |" + lineSeparator() +
            "  +---+" + lineSeparator() +
            "    1  " + lineSeparator();
    private final String whiteTurn = "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator();
    private final String blackTurn = "Black it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator();
    private final String wholeNormalTurn = whiteTurn + blackTurn;
    private final String playersColours = "White has colour WHITE and his symbol is " + white + lineSeparator() +
            "Black has colour BLACK and his symbol is " + black + lineSeparator();
    private final String drawPrint = "Draw: both players have the same number of live stones: ";


    @Test
    public void testWhiteWinner() {
        ApplicationRunner application = new ApplicationRunner(4);
        application.parseWinner();
        application.testOutput("White won with " + 12 + " live stones against Black's " + 0 + lineSeparator());
    }

    @Test
    public void testDraw() {
        ApplicationRunner application = new ApplicationRunner(4);
        application.parseDraw();
        application.testOutput("Draw: both players have the same number of live stones: " + 4 + lineSeparator());
    }

    @Test
    public void testPlay() {
        ApplicationRunner application = new ApplicationRunner(1);
        application.parsePlay("1 1");
        application.testOutput(playersColours + initialBoardOfDim1 + whiteTurn +
                finalBoardOfDim1 + drawPrint + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithInvalidInteger() {
        ApplicationRunner application = new ApplicationRunner(1);
        String input = "q 1" + System.lineSeparator() + "1";
        application.parsePlay(input);
        application.testOutput(playersColours + initialBoardOfDim1 + whiteTurn +
                "You didn't enter an integer! Enter again an integer" + lineSeparator() +
                finalBoardOfDim1 + drawPrint + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithPositionOutsideBoard() {
        ApplicationRunner application = new ApplicationRunner(1);
        String input = "1 0" + System.lineSeparator() + "1 1";
        application.parsePlay(input);
        application.testOutput(playersColours + initialBoardOfDim1 + whiteTurn +
                "The position is not inside the board!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                finalBoardOfDim1 + drawPrint + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithPositionAlreadyOccupied() {
        ApplicationRunner application = new ApplicationRunner(2);
        String input = "1 1" + lineSeparator() + "1 1" + lineSeparator() +
                "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2";
        application.parsePlay(input);
        String output = playersColours + wholeNormalTurn +
                "The position is already occupied!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                wholeNormalTurn + drawPrint + 0 + lineSeparator();
        application.testOutputWithoutBoardPrints(output);
    }

    @Test
    public void testPlayWithNotAdjacentPosition() {
        ApplicationRunner application = new ApplicationRunner(3);
        String input = "1 1" + lineSeparator() + "1 3" + lineSeparator() +
                "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2" + lineSeparator() + " 1 3" + lineSeparator() +
                "2 3" + lineSeparator() + "3 3" + lineSeparator() + "3 2" + lineSeparator() + "3 1";
        application.parsePlay(input);
        String output = playersColours + wholeNormalTurn +
                "The position is not adjacent to the previous one!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                wholeNormalTurn.repeat(3) + whiteTurn + drawPrint + 0 + lineSeparator();
        application.testOutputWithoutBoardPrints(output);
    }

    @Test
    public void testPlayWithFreedomChoice() {
        ApplicationRunner application = new ApplicationRunner(3);
        String input = "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2" + lineSeparator() +
                " 1 1" + lineSeparator() + "1 3" + lineSeparator() + "2 3" + lineSeparator() +
                "3 3" + lineSeparator() + "3 2" + lineSeparator() + "3 1";
        application.parsePlay(input);
        String output = playersColours + wholeNormalTurn.repeat(4) +
                whiteTurn + drawPrint + 0 + lineSeparator();
        application.testOutputWithoutBoardPrints(output);
    }

    @Test
    public void testLastMoveChoice() {
        int boardSize = 5;
        ApplicationRunner application = new ApplicationRunner(boardSize);
        String input = buildInputMoves(boardSize);
        application.parsePlay(input);
        String output = playersColours + wholeNormalTurn.repeat(12) +
                whiteTurn + "Do you want to do the last move? (0 = no, 1 = yes)" + lineSeparator() +
                "Black won with 12 live stones against White's 4" + lineSeparator();
        application.testOutputWithoutBoardPrints(output);
    }

    private String buildInputMoves(int boardSize) {
        String input = "";
        for (int i = 1; i <= boardSize; ++i) {
            if (i % 2 == 1) {
                for (int j = 1; j <= boardSize; ++j) {
                    input += i + " " + j + lineSeparator();
                }
            } else {
                for (int j = boardSize; j >= 1; --j) {
                    input += i + " " + j + lineSeparator();
                }
            }
        }
        return input + "0";
    }

    @Test
    public void testPrintBoard() {
        ApplicationRunner application = new ApplicationRunner(4);
        String white = Utility.getWhite();
        String black = Utility.getBlack();
        List<Position> positions = Arrays.asList(Position.at(1, 1), Position.at(2, 1));
        List<Colour> colours = Arrays.asList(Colour.WHITE, Colour.BLACK);
        application.parseBoard(positions, colours);
        String line = "  " + "+---".repeat(4) + "+" + lineSeparator();
        String boardAfterSecondMove =
                line + "4 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line + "3 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line + "2 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line +
                        "1 " + "| " + white + " | " + black + " |  ".repeat(2) + " |" + lineSeparator() +
                        line + "    1   2   3   4  " + lineSeparator();
        application.testOutput(boardAfterSecondMove);
    }
}
