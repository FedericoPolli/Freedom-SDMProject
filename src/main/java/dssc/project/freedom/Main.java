package dssc.project.freedom;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the board size (minimum 4):");
        int boardSize = 4;
        boolean flag = true;
        while (flag){
            try {
                boardSize = in.nextInt();
                if (boardSize >= 4)
                    flag = false;
            } catch (InputMismatchException e){
                System.out.println("Boardsize must be an integer >= 4");
            }
        }
        Game game = new Game(boardSize);
        Colour colour;
        for (int i = 1; i <= boardSize*boardSize; ++i){
            if (i%2 != 0){
                System.out.println("White it's your turn!");
                colour = Colour.WHITE;
            } else {
                System.out.println("Black it's your turn!");
                colour = Colour.BLACK;
            }
            Position current;
            do {
                System.out.println("Enter the x and y coordinates of the stone:");
                // add some print
                int x = in.nextInt();
                int y = in.nextInt();
                current = Position.at(x, y);
            }while (!game.isMoveValid(current));
            if (i == boardSize*boardSize){
                if (!game.isLastMoveConvenient(current, colour)){
                    System.out.println("Do you want to do the last move?(0 = yes, 1= no)");
                    int d = in.nextInt();
                    if (d==1){
                        break;
                    }
                }
            }
            game.play(current, colour);
        }

        game.winner();
    }
}
