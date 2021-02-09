package dssc.project.freedom;

import java.util.Scanner;
import static dssc.project.freedom.Utility.getInteger;

public class CommandLineGame extends Game{

    /**
     * Class constructor. A {@link Game} has a {@link Board} on which the players play.
     *
     * @param boardSize The size of the Board to be created.
     */
    public CommandLineGame(int boardSize) {
        super(boardSize);
    }

    public void play(Scanner in) {
        for (int i = 1; i <= board.boardSize * board.boardSize; ++i){
            Colour colour = getPlayerColour(i);
            Position current = getValidPosition(in);
            if (isLastMove(board.boardSize, i) && userDoesNotWantToDoLastMove(in, colour, current)) break;
            move(current, colour);
        }
        winner();
    }

    @Override
    public void move(Position current, Colour colour) {
        super.move(current, colour);
        board.printBoard();
    }

    @Override
    public Colour winner(){
        board.checkBoardAndMakeStonesLive();
        int whiteLiveStones = board.countLiveStones(Colour.WHITE);
        int blackLiveStones = board.countLiveStones(Colour.BLACK);
        if (whiteLiveStones > blackLiveStones) {
            System.out.println("White won with " + whiteLiveStones + " live stones against Black's " + blackLiveStones);
            return Colour.WHITE;
        } else if (blackLiveStones > whiteLiveStones) {
            System.out.println("Black won with " + blackLiveStones + " live stones against White's " + whiteLiveStones);
            return Colour.BLACK;
        } else {
            System.out.println("Draw: both players have the same number of live stones: " + whiteLiveStones);
            return Colour.NONE;
        }
    }

    private boolean userDoesNotWantToDoLastMove(Scanner in, Colour colour, Position current) {
        if (!isLastMoveConvenient(current, colour)){
            System.out.println("Do you want to do the last move? (0 = yes, 1 = no)");
            int d = getInteger(in);
            return d != 0;
        }
        return false;
    }

    private boolean isLastMove(int boardSize, int i) {
        return i == boardSize * boardSize;
    }

    private Position getValidPosition(Scanner in) {
        Position current;
        int i = 0;
        do {
            if (i>0){
                System.out.print("You have inserted a wrong position!");
            }
            System.out.println(" Enter the x and y coordinates of the stone:");
            int x = getInteger(in);
            int y = getInteger(in);
            current = Position.at(x, y);
            ++i;
        } while (!isMoveValid(current));
        return current;
    }

    private Colour getPlayerColour(int i) {
        Colour colour;
        if (i % 2 != 0){
            System.out.print("White it's your turn!");
            colour = Colour.WHITE;
        } else {
            System.out.print("Black it's your turn!");
            colour = Colour.BLACK;
        }
        return colour;
    }
}

