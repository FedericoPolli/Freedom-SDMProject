package dssc.project.freedom.players;

import dssc.project.freedom.utilities.RandomGenerator;
import dssc.project.freedom.basis.Colour;
import org.junit.jupiter.api.Test;

import static dssc.project.freedom.basis.Position.at;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomPlayerTests {

    @Test
    public void RandomPosition() {
        RandomGenerator randomGenerator = new FakeRandomGenerator();
        Player player = new RandomPlayer("White", Colour.WHITE, 5, randomGenerator);
        assertAll(
                () -> assertEquals(at(1,2), player.getPlayerPosition()),
                () -> assertEquals(at(3,4), player.getPlayerPosition()),
                () -> assertEquals(at(5,1), player.getPlayerPosition())
        );
    }

    /**
     * Fake implementation of the interface {@link RandomGenerator} to use for testing.
     */
    public class FakeRandomGenerator implements RandomGenerator {

        /**
         * Number of calls of the getRandomInteger function
         */
        private int lastCall = -1;
        /**
         * Return the number of times the function has been called. If the number is higher or equal to a given
         * upperbound the counter is set to 0.
         * @param upperBound The upperbound of the number of calls
         * @return An integer representing the number of calls of the function.
         */
        @Override
        public int getRandomInteger(int upperBound) {
            ++lastCall;
            if (lastCall >= upperBound)
                lastCall = 0;
            return lastCall;
        }
    }
}

