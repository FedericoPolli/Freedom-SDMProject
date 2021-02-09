package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static java.lang.System.lineSeparator;

public class CLGameTest {

    @Test
    public void testWinner() {
        ApplicationRunner application = new ApplicationRunner();
        application.parseWinner();
        application.testOutput("Draw: both players have the same number of live stones: " + 4 + lineSeparator());
    }

    @Test
    public void testPlay() {
        ApplicationRunner application = new ApplicationRunner();
        application.parsePlay("1 1");
        String board = "+---+" + lineSeparator() +
                "| \u26AA |" + lineSeparator() +
                "+---+" + lineSeparator();
        application.testOutput("White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
            board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithWrongPosition() {
        ApplicationRunner application = new ApplicationRunner();
        String input = "1 0" + System.lineSeparator() + "1 1";
        application.parsePlay(input);
        String board = "+---+" + lineSeparator() +
                "| \u26AA |" + lineSeparator() +
                "+---+" + lineSeparator();
        application.testOutput("White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                        "You have inserted a wrong position!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }

    @Test
    public void testPlayWithInvalidInteger() {
        ApplicationRunner application = new ApplicationRunner();
        String input = "q 1" + System.lineSeparator() + "1";
        application.parsePlay(input);
        String board = "+---+" + lineSeparator() +
                "| \u26AA |" + lineSeparator() +
                "+---+" + lineSeparator();
        application.testOutput("White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
                "You didn't enter an integer! Enter again an integer" + lineSeparator() +
                board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }
}
