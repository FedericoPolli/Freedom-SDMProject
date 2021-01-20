package dssc.project.freedom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StoneTests {

    @Test
    void checkWhite() {
        Stone stone = new Stone(Colour.WHITE);
        assertTrue(stone.isWhite());
    }

    @Test
    void checkBlack() {
        Stone stone = new Stone(Colour.BLACK);
        assertTrue(stone.isBlack());
    }
}
