package dssc.project.freedom;

import dssc.project.freedom.players.Player;

import java.util.Scanner;
import static dssc.project.freedom.Utility.getInteger;

public class CommandLineGame extends Game{

    Player player1;
    Player player2;

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
            board.printBoard();
        }
        winner();
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
        do {
            System.out.println(" Enter the x and y coordinates of the stone:");
            int x = getInteger(in);
            int y = getInteger(in);
            current = Position.at(x, y);
        } while (!isMoveValid(current));
        return current;
    }

    /**
     * Checks if the move of the player is valid and prints a message to the user.
     * A move is valid if the {@link Position} in which the {@link Stone} is
     * placed is inside the {@link Board}, if it is not on an already occupied
     * {@link Position} and if it is adjacent to the previous played {@link Stone}
     * in the case in which the adjacent {@link Position}s of the previous
     * played {@link Stone} are not all occupied, otherwise the player has the freedom
     * of placing it in any non-occupied {@link Position}.
     * @param current The Position of the Stone placed in the move that has to be checked.
     * @return true if the move of the player is valid, false otherwise.
     */
    public boolean isMoveValid(Position current) {
        if (!board.positionIsInsideTheBoard(current)) {
            System.out.print("The position is not inside the board!");
            return false;
        }
        if (board.stoneIsAlreadyPlacedAt(current)) {
            System.out.print("The position is already occupied!");
            return false;
        }
        if (anyPositionAdjacentToPreviousOneIsFree()) {
            if (current.isInAdjacentPositions(previous)) {
                return true;
            } else {
                System.out.print("The position is not adjacent to the previous one!");
                return false;
            }
        }
        return true;
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

