package dssc.project.freedom;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the board size (minimum 4):");
        int boardSize;
        boolean flag;
        do {
            flag = false;
            boardSize = getInteger(in);
            if (boardSize < 4) {
                flag = true;
                System.out.println("The size of the board must be an integer >= 4");
            }
        } while (flag);
        Game game = new Game(boardSize);
        Colour colour;
        for (int i = 1; i <= boardSize*boardSize; ++i){
            if (i % 2 != 0){
                System.out.println("White it's your turn!");
                colour = Colour.WHITE;
            } else {
                System.out.println("Black it's your turn!");
                colour = Colour.BLACK;
            }
            Position current;
            do {
                System.out.println("Enter the x and y coordinates of the stone:");
                int x = getInteger(in);
                int y = getInteger(in);
                current = Position.at(x, y);
            } while (!game.isMoveValid(current));
            if (i == boardSize * boardSize){
                if (!game.isLastMoveConvenient(current, colour)){
                    System.out.println("Do you want to do the last move? (0 = yes, 1 = no)");
                    int d = getInteger(in);
                    if (d != 0){
                        break;
                    }
                }
            }
            game.play(current, colour);
        }
        game.winner();
    }

    private static int getInteger(Scanner in) {
        boolean flag = false;
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
