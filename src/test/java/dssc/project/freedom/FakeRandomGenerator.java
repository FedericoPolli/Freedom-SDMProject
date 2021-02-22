package dssc.project.freedom;

/**
 * Fake implementation of the interface {@link RandomGenerator} to use for testing.
 */
public class FakeRandomGenerator implements RandomGenerator {

    /**
     * Number of calls of the getRandomInteger function
     */
    private int lastCall = 0;
    /**
     * Return the number of times the function has been called. If the number is higher then a given upperbound the
     * the counter is set to 0.
     * @param upperBound The upperbound of the number of calls
     * @return An integer representing the number of calls of the function.
     */
    @Override
    public int getRandomInteger(int upperBound) {
        if (lastCall >= upperBound)
            lastCall = 0;
        return ++lastCall;
    }
}
