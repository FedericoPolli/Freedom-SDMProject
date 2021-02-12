package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static java.lang.System.lineSeparator;

public class CLGameTests {

    String white = Utility.getWhite();
    String black = Utility.getBlack();

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
        String board = "+---+" + lineSeparator() +
                "| "+ white + " |" + lineSeparator() +
                "+---+" + lineSeparator();
        application.testOutput("White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
            board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithInvalidInteger() {
        ApplicationRunner application = new ApplicationRunner(1);
        String input = "q 1" + System.lineSeparator() + "1";
        application.parsePlay(input);
        String board = "+---+" + lineSeparator() +
                "| "+ white + " |" + lineSeparator() +
                "+---+" + lineSeparator();
        application.testOutput("White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "You didn't enter an integer! Enter again an integer" + lineSeparator() +
                board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithPositionOutsideBoard() {
        ApplicationRunner application = new ApplicationRunner(1);
        String input = "1 0" + System.lineSeparator() + "1 1";
        application.parsePlay(input);
        String board = "+---+" + lineSeparator() +
                "| "+ white + " |" + lineSeparator() +
                "+---+" + lineSeparator();
        application.testOutput("White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "The position is not inside the board!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithPositionAlreadyOccupied() {
        ApplicationRunner application = new ApplicationRunner(2);
        String input = "1 1" + lineSeparator() + "1 1" + lineSeparator() +
                "1 2" + lineSeparator() + "2 1" + lineSeparator() + "2 2";
        application.parsePlay(input);
        String output = "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "Black it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "The position is already occupied!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "Black it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
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
        String getCoordinatesPlayers = "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "Black it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator();
        String output = getCoordinatesPlayers +
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
        String getCoordinatesPlayers = "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "Black it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator();
        String output = getCoordinatesPlayers.repeat(4) +
                "White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "Draw: both players have the same number of live stones: " + 0 + lineSeparator();
        application.testOutputWithoutBoardPrints(output);
    }
}
