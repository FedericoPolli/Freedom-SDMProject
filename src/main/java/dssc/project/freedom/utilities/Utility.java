package dssc.project.freedom.utilities;

import java.util.Scanner;

/**
 * Class that contains functions that are useful for various classes.
 */
public class Utility {

    /**
     * Takes in input an effective integer. It keeps asking the user an integer
     * until an integer value is inserted.
     * @param in The Scanner on which reading the input.
     * @return The integer taken in input.
     */
    public static int getInteger(Scanner in) {
        int i;
        do {
            if (in.hasNextInt()) {
                i = in.nextInt();
                break;
            } else {
                System.out.println("You didn't enter an integer! Enter again an integer");
                in.next();
            }
        } while (true);
        return i;
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
     * Returns the symbol to be used for the white {@link dssc.project.freedom.basis.Stone}s.
     * @return The symbol for white Stones.
     */
    public static String getWhite() {
        return getOS().equals("Windows") ? "w" : "\u26AA";
    }

    /**
     * Returns the symbol to be used for the black {@link dssc.project.freedom.basis.Stone}s.
     * @return The symbol for black Stones.
     */
    public static String getBlack() {
        return getOS().equals("Windows") ? "b" : "\u26AB";
    }
}