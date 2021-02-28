package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static java.lang.System.lineSeparator;

public class GameHandlerTests {

    @Test
    void testWrongBoardSize(){
        ApplicationRunner application = new ApplicationRunner();
        String input = "2 4";
        String output = "Enter the board size (minimum 4): "
                + "The size of the board must be an integer >= 4! ";
        application.parseBoardSize(input, output, 4);
    }

    @Test
    void testWrongTypeOfPlayer() {
        ApplicationRunner application = new ApplicationRunner();
        String input = "t" + lineSeparator() + "g" + lineSeparator();
        String output = "Choose the player: 'h' for a Human Player, 'r' for a Random Player or 'g' for a Greedy Player. "
                + "Wrong type of player! Reenter it: ";
        application.parseTypeOfPlayer(input, output, 'g');
    }
}