package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static java.lang.System.lineSeparator;

public class GameHandlerTests {

    @Test
    void testWrongBoardSize(){
        String input = "2 4";
        ApplicationRunner application = new ApplicationRunner(input);
        String output = "Enter the board size (minimum 4): "
                + "The size of the board must be an integer >= 4! ";
        application.parseBoardSize(input, output, 4);
    }

    @Test
    void testWrongTypeOfPlayer() {
        String input = "t" + lineSeparator() + "g" + lineSeparator(input);
        ApplicationRunner application = new ApplicationRunner(input);
        String output = "Choose the player: 'h' for a Human Player, 'r' for a Random Player or 'g' for a Greedy Player. "
                + "Wrong type of player! Reenter it: ";
        application.parseTypeOfPlayer(input, output, 'g');
    }

    @Test
    void replayGameAndSwitchColours() {
        String input = "1 1";
        ApplicationRunner application = new ApplicationRunner(input);
        application.parseGameWithGivenSettings(input);
    }
}