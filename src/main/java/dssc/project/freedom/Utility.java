package dssc.project.freedom;

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
                System.out.println("You didn't enter an integer!");
                in.next();
                flag = true;
            }
        } while(flag);
        return i;
    }
}
