package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static java.lang.System.lineSeparator;

public class CLGameTest {

    @Test
    public void testWinner() {
        ApplicationRunner application = new ApplicationRunner();
        application.parseWinner();
        application.showsMove("Draw: both players have the same number of live stones: " + 4 + lineSeparator());
    }
}
