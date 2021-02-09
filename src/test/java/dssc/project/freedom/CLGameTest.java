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
        application.parsePlay();
        String board = "+---+" + lineSeparator() +
                "| \u26AA |" + lineSeparator() +
                "+---+" + lineSeparator();
        application.testOutput("White it's your turn!" + " Enter the x and y coordinates of the stone:" + lineSeparator() +
            board + "Draw: both players have the same number of live stones: " + 0 + lineSeparator());
    }
}
