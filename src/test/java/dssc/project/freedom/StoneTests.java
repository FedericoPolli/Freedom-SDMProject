package dssc.project.freedom;

import org.junit.jupiter.api.Test;

import static dssc.project.freedom.Colour.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StoneTests {

    // isOfCoulor()
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
    void checkNone() {
        Stone stone = new Stone(NONE);
        assertTrue(stone.isOfColour(NONE));
    }

    // !isOfCoulor()
    @Test
    void checkNotBlack() {
        Stone stone = new Stone(WHITE);
        assertFalse(stone.isOfColour(BLACK));
    }

    @Test
    void checkNotWhite() {
        Stone stone = new Stone(BLACK);
        assertFalse(stone.isOfColour(WHITE));
    }

    // isOfSameColourAs
    @Test
    void checkBothWhite() {
        Stone stone = new Stone(WHITE);
        Stone other = new Stone(WHITE);
        assertTrue(stone.isOfSameColourAs(other));
    }

    @Test
    void checkBothBlack() {
        Stone stone = new Stone(BLACK);
        Stone other = new Stone(BLACK);
        assertTrue(stone.isOfSameColourAs(other));
    }

    // !isOfSameColour
    @Test
    void checkBlackAndWhite() {
        Stone stone = new Stone(WHITE);
        Stone other = new Stone(BLACK);
        assertFalse(stone.isOfSameColourAs(other));
    }

    @Test
    void checkBlackAndNone() {
        Stone stone = new Stone(BLACK);
        Stone other = new Stone(NONE);
        assertFalse(stone.isOfSameColourAs(other));
    }

    // makeColoured
    @Test
    void checkWhiteToBlack() {
        Stone stone = new Stone(WHITE);
        stone.makeOfColour(BLACK);
        assertFalse(stone.isOfColour(BLACK));
    }

    @Test
    void checkNoneToWhite() {
        Stone stone = new Stone(NONE);
        stone.makeOfColour(WHITE);
        assertFalse(stone.isOfColour(WHITE));
    }

    @Test
    void checkBlackToNone() {
        Stone stone = new Stone(BLACK);
        stone.makeOfColour(NONE);
        assertFalse(stone.isOfColour(NONE));
    }

    // isLive

    @Test
    void checkIfLive() {
        Stone stone = new Stone(WHITE);
        assertFalse(stone.isLive());
    }

    // changeLiveStatusTo

    @Test
    void checkChangeLiveStatus() {
        Stone stone = new Stone(WHITE);
        stone.changeLiveStatusTo(true);
        assertTrue(stone.isLive());
    }
    

    @Test
    void checkIfColoured() {
        Stone stone = new Stone(Colour.NONE);
        stone.makeOfColour(WHITE);
        assertTrue(stone.isOfColour(WHITE));
    }
}
