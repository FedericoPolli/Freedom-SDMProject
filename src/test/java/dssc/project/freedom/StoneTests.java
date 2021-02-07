package dssc.project.freedom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static dssc.project.freedom.Colour.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StoneTests {

    @ParameterizedTest
    @CsvSource({"WHITE, WHITE", "BLACK, BLACK", "NONE, NONE"})
    void checkSameColours(Colour colour, Colour expectedColour){
        Stone stone = new Stone(colour);
        assertTrue(stone.isOfColour(expectedColour));
    }

    @ParameterizedTest
    @CsvSource({"WHITE, BLACK", "BLACK, WHITE"})
    void checkWrongColours(Colour colour, Colour expectedColour){
        Stone stone = new Stone(colour);
        assertFalse(stone.isOfColour(expectedColour));
    }

    @ParameterizedTest
    @CsvSource({"WHITE, WHITE", "BLACK, BLACK"})
    void checkStonesOfTheSameColour(Colour firstColour, Colour otherColour){
        Stone stone = new Stone(firstColour);
        Stone other = new Stone(otherColour);
        assertTrue(stone.isOfSameColourAs(other));
    }

    @ParameterizedTest
    @CsvSource({"WHITE, BLACK", "BLACK, NONE"})
    void checkStonesOfDifferentColours(Colour firstColour, Colour otherColour){
        Stone stone = new Stone(firstColour);
        Stone other = new Stone(otherColour);
        assertFalse(stone.isOfSameColourAs(other));
    }

    @ParameterizedTest
    @CsvSource({"WHITE, BLACK", "NONE, WHITE", "BLACK, NONE"})
    void checkChangeOfColour(Colour originalColour, Colour changedColour){
        Stone stone = new Stone(originalColour);
        stone.makeOfColour(changedColour);
        assertTrue(stone.isOfColour(changedColour));
    }

    @Test
    void checkIfLive() {
        Stone stone = new Stone(WHITE);
        assertFalse(stone.isLive());
    }

    @Test
    void checkChangeLiveStatus() {
        Stone stone = new Stone(WHITE);
        stone.changeLiveStatusTo(true);
        assertTrue(stone.isLive());
    }
}
