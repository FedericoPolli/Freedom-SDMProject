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
        } while(flag);
        return i;
    }


    public static String getOS() {
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) return "Windows";
        else return "Not Windows";
    }

    public static String getWhite() {
        return getOS().equals("Windows") ? "w" : "\u26AA";
    }

    public static String getBlack() {
        return getOS().equals("Windows") ? "b" : "\u26AB";
    }

    public static int getRandomInteger(int upperBound){
        return new Random().nextInt(upperBound);
    }
}
