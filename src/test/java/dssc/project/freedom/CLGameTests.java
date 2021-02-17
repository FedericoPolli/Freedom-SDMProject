package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static java.lang.System.lineSeparator;

public class CLGameTests {

    final String white = Utility.getWhite();
    final String black = Utility.getBlack();
    String getCoordinatesPlayers = "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
            "Black it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator();
    String playersColours = "White has colour WHITE and his symbol is " + white + lineSeparator() +
                            "Black has colour BLACK and his symbol is " + black + lineSeparator();

    @Test
    public void testWhiteWinner () {
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
        String initialBoard = "  +---+" + lineSeparator() +
                "1 |   |" + lineSeparator() +
                "  +---+" + lineSeparator() +
                "    1  " + lineSeparator();
        String board = "  +---+" + lineSeparator() +
                "1 | "+ white + " |" + lineSeparator() +
                "  +---+" + lineSeparator() +
                "    1  " + lineSeparator();
        application.testOutput(playersColours + initialBoard + "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
            board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithInvalidInteger() {
        ApplicationRunner application = new ApplicationRunner(1);
        String input = "q 1" + System.lineSeparator() + "1";
        application.parsePlay(input);
        String initialBoard = "  +---+" + lineSeparator() +
                "1 |   |" + lineSeparator() +
                "  +---+" + lineSeparator() +
                "    1  " + lineSeparator();
        String board = "  +---+" + lineSeparator() +
                "1 | "+ white + " |" + lineSeparator() +
                "  +---+" + lineSeparator() +
                "    1  " + lineSeparator();
        application.testOutput(playersColours + initialBoard + "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "You didn't enter an integer! Enter again an integer" + lineSeparator() +
                board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithPositionOutsideBoard() {
        ApplicationRunner application = new ApplicationRunner(1);
        String input = "1 0" + System.lineSeparator() + "1 1";
        application.parsePlay(input);
        String initialBoard = "  +---+" + lineSeparator() +
                "1 |   |" + lineSeparator() +
                "  +---+" + lineSeparator() +
                "    1  " + lineSeparator();
        String board = "  +---+" + lineSeparator() +
                "1 | "+ white + " |" + lineSeparator() +
                "  +---+" + lineSeparator() +
                "    1  " + lineSeparator();
        application.testOutput(playersColours + initialBoard + "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "The position is not inside the board!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithPositionAlreadyOccupied() {
        ApplicationRunner application = new ApplicationRunner(2);
        String input = "1 1" + lineSeparator() + "1 1" + lineSeparator() +
                "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2";
        application.parsePlay(input);
        String output = playersColours + getCoordinatesPlayers +
                "The position is already occupied!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                getCoordinatesPlayers +
                "Draw: both players have the same number of live stones: " + 0 + lineSeparator();
        application.testOutputWithoutBoardPrints(output);
    }

    @Test
    public void testPlayWithNotAdjacentPosition() {
        ApplicationRunner application = new ApplicationRunner(3);
        String input = "1 1" + lineSeparator() + "1 3" + lineSeparator() +
                "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2" + lineSeparator() + " 1 3" + lineSeparator() +
                "2 3" + lineSeparator() + "3 3" + lineSeparator() + "3 2" + lineSeparator() + "3 1" ;
        application.parsePlay(input);
        String output = playersColours + getCoordinatesPlayers +
                "The position is not adjacent to the previous one!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                getCoordinatesPlayers.repeat(3) +
                "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "Draw: both players have the same number of live stones: " + 0 + lineSeparator();
        application.testOutputWithoutBoardPrints(output);
    }

    @Test
    public void testPlayWithFreedomChoice() {
        ApplicationRunner application = new ApplicationRunner(3);
        String input = "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2" + lineSeparator() +
                " 1 1" + lineSeparator() + "1 3" + lineSeparator()+ "2 3" + lineSeparator() +
                "3 3" + lineSeparator() + "3 2" + lineSeparator() + "3 1" ;
        application.parsePlay(input);
        String output = playersColours + getCoordinatesPlayers.repeat(4) +
                "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "Draw: both players have the same number of live stones: " + 0 + lineSeparator();
        application.testOutputWithoutBoardPrints(output);
    }

    @Test
    public void testLastMoveChoice() {
        int boardSize = 5;
        ApplicationRunner application = new ApplicationRunner(boardSize);
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
        input += "1";
        application.parsePlay(input);
        String output = playersColours + getCoordinatesPlayers.repeat(12) +
                "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "Do you want to do the last move? (0 = yes, 1 = no)" + lineSeparator() +
                "Black won with 12 live stones against White's 4" + lineSeparator();
        application.testOutputWithoutBoardPrints(output);
    }
    
    @Test
    public void testPrintBoard(){
        ApplicationRunner application = new ApplicationRunner(4);
        String white = Utility.getWhite();
        String black = Utility.getBlack();
        application.parseBoard(Position.at(1, 1), Colour.WHITE);
        application.parseBoard(Position.at(2, 1), Colour.BLACK);
        String line = "  " + "+---".repeat(4) + "+" + lineSeparator();
        String boardAfterFirstMove =
                line + "4 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line + "3 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line + "2 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line + "1 " + "| " + white + " |" + "   |".repeat(3) + lineSeparator() +
                        line + "    1   2   3   4  " + lineSeparator();
        String boardAfterSecondMove =
                line + "4 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line + "3 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line + "2 " + "|   ".repeat(4) + "|" + lineSeparator() +
                        line +
                        "1 " + "| " + white + " | " + black + " |  ".repeat(2) + " |" + lineSeparator() +
                        line + "    1   2   3   4  " + lineSeparator();
        application.testOutput(boardAfterFirstMove + boardAfterSecondMove);
    }
}
