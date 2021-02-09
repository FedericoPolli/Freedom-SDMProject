package dssc.project.freedom;

import java.util.InputMismatchException;
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
            try {
                i = in.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.println("You didn't enter an integer!");
                flag = true;
            }
        } while(flag);
        return i;
    }
}
