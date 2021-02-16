package dssc.project.freedom;

import java.util.Random;
import java.util.Scanner;

public class Utility {

    /**
     * Takes in input an effective integer. It keeps asking the user an integer
     * until an integer value is inserted.
     * @param in The Scanner on which reading the input.
     * @return The integer taken in input.
     */
    public static int getInteger(Scanner in) {
        boolean flag;
        int i = 0;
        do {
            if (in.hasNextInt()) {
                flag = false;
                i = in.nextInt();
            } else {
                System.out.println("You didn't enter an integer! Enter again an integer");
                in.next();
                flag = true;
            }
        } while (flag);
        return i;
    }

    /**
     * Returns a randomly generated integer number.
     * @param upperBound The upper bound for the random number.
     * @return The randomly generated integer.
     */
    public static int getRandomInteger(int upperBound) {
        return new Random().nextInt(upperBound);
    }

    /**
     * Returns if the OS on which the program is running is Windows or not.
     * @return A String which tells if the OS is Windows or not.
     */
    public static String getOS() {
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) return "Windows";
        else return "Not Windows";
    }

    /**
     * Returns the symbol to be used for the white {@link Stone}s.
     * @return The symbol for white Stones.
     */
    public static String getWhite() {
        return getOS().equals("Windows") ? "w" : "\u26AA";
    }

    /**
     * Returns the symbol to be used for the black {@link Stone}s.
     * @return The symbol for black Stones.
     */
    public static String getBlack() {
        return getOS().equals("Windows") ? "b" : "\u26AB";
    }
}
