package dssc.project.freedom;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandLineGame {

    /**
     */
    public void play() {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the board size (minimum 4):");
        int boardSize = getBoardSize(in);
        Game game = new Game(boardSize);
        for (int i = 1; i <= boardSize * boardSize; ++i){
            Colour colour = getPlayerColour(i);
            Position current = getValidPosition(in, game);
            if (isLastMove(boardSize, i) && userWantToDoLastMove(in, game, colour, current)) break;
            game.move(current, colour);
        }
        game.winner();
    }

    private boolean userWantToDoLastMove(Scanner in, Game game, Colour colour, Position current) {
        if (!game.isLastMoveConvenient(current, colour)){
            System.out.println("Do you want to do the last move? (0 = yes, 1 = no)");
            int d = getInteger(in);
            return d != 0;
        }
        return false;
    }

    private boolean isLastMove(int boardSize, int i) {
        return i == boardSize * boardSize;
    }

    private Position getValidPosition(Scanner in, Game game) {
        Position current;
        int i = 0;

        do {
            if (i>0){
                System.out.println("You have inserted a wrong position! Try again");
            }
            System.out.println("Enter the x and y coordinates of the stone:");
            int x = getInteger(in);
            int y = getInteger(in);
            current = Position.at(x, y);
            ++i;
        } while (!game.isMoveValid(current));
        return current;
    }

    private Colour getPlayerColour(int i) {
        Colour colour;
        if (i % 2 != 0){
            System.out.println("White it's your turn!");
            colour = Colour.WHITE;
        } else {
            System.out.println("Black it's your turn!");
            colour = Colour.BLACK;
        }
        return colour;
    }

    private int getBoardSize(Scanner in) {
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
        return boardSize;
    }

    /**
     * Takes in input an effective integer. It keeps asking the user an integer
     * until an integer value is inserted.
     * @param in The Scanner on which reading the input.
     * @return The integer taken in input.
     */
    private int getInteger(Scanner in) {
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

