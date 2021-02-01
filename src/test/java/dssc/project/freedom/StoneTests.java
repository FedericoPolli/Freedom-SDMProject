package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static dssc.project.freedom.Colour.BLACK;
import static dssc.project.freedom.Colour.WHITE;
import static org.junit.jupiter.api.Assertions.*;


public class StoneTests {

    @Test
    void checkWhite() {
        Stone stone = new Stone(WHITE);
        assertTrue(stone.isOfColour(WHITE));
    }

    @Test
    void checkBlack() {
        Stone stone = new Stone(BLACK);
        assertTrue(stone.isOfColour(BLACK));
    }

    @Test
    void checkNotBlack() {
        Stone stone = new Stone(WHITE);
        assertFalse(stone.isOfColour(BLACK));
    }

    @Test
    void checkIfLive() {
        Stone stone = new Stone(WHITE);
        assertFalse(stone.isLive());
    }

    @Test
    void checkIfColoured() {
        Stone stone = new Stone(Colour.NONE);
        stone.makeColoured(WHITE);
        assertTrue(stone.isOfColour(WHITE));
    }
}
