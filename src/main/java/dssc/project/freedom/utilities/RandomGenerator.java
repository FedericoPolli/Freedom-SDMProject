package dssc.project.freedom.utilities;

/**
 * Interface representing a Random Generator.
 */
public interface RandomGenerator {

    /**
     * Returns a randomly generated integer number.
     * @param upperBound The upper bound for the random number.
     * @return A randomly generated integer number.
     */
    int getRandomInteger(int upperBound);
}