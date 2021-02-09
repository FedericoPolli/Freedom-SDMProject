package dssc.project.freedom;

import org.junit.jupiter.api.Test;

public class CLGameTest {

    @Test
    public void testMove(){
        ApplicationRunner application = new ApplicationRunner();

        application.parseMove(Position.at(1, 1), Colour.WHITE);
        application.parseMove(Position.at(2, 1), Colour.BLACK);
        String board = "+---".repeat(4) + "+" + System.lineSeparator() +
                "|   ".repeat(4) + "|" + System.lineSeparator() +
                "+---".repeat(4) + "+" + System.lineSeparator() +
                "|   ".repeat(4) + "|" + System.lineSeparator() +
                "+---".repeat(4) + "+" + System.lineSeparator() +
                "|   ".repeat(4) + "|" + System.lineSeparator() +
                "+---".repeat(4) + "+" + System.lineSeparator() +
                "| " + '\u26AA' + " |  ".repeat(3) + " |" + System.lineSeparator() +
                "+---".repeat(4) + "+" + System.lineSeparator();
        String board2 = "+---".repeat(4) + "+" + System.lineSeparator() +
                "|   ".repeat(4) + "|" + System.lineSeparator() +
                "+---".repeat(4) + "+" + System.lineSeparator() +
                "|   ".repeat(4) + "|" + System.lineSeparator() +
                "+---".repeat(4) + "+" + System.lineSeparator() +
                "|   ".repeat(4) + "|" + System.lineSeparator() +
                "+---".repeat(4) + "+" + System.lineSeparator() +
                "| " + '\u26AA' + " | " +'\u26AB' + " |  ".repeat(2) + " |" + System.lineSeparator() +
                "+---".repeat(4) + "+" + System.lineSeparator();
        application.showsMove(board + board2);
    }
}
