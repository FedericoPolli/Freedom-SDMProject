package dssc.project.freedom;

import dssc.project.freedom.players.*;

public class CommandLineGame extends Game {

    private Player player1;
    private Player player2;

    /**
     * Class constructor. A {@link Game} has a {@link Board} on which the players
     * play, then it has two players.
     * @param boardSize The size of the Board to be created.
     */
    public CommandLineGame(int boardSize, char player1, String name1, char player2, String name2) {
        super(boardSize);
        switch (player1) {
            case 'h' -> this.player1 = new HumanPlayer(name1, Colour.WHITE);
            case 'r' -> this.player1 = new RandomPlayer(name1, Colour.WHITE, boardSize);
            case 'g' -> this.player1 = new GreedyPlayer(name1, Colour.WHITE);
        }
        switch (player2) {
            case 'h' -> this.player2 = new HumanPlayer(name2, Colour.BLACK);
            case 'r' -> this.player2 = new RandomPlayer(name2, Colour.BLACK, boardSize);
            case 'g' -> this.player2 = new GreedyPlayer(name2, Colour.BLACK);
        }
    }

    public void play() {
        System.out.println(player1.getName() + " has colour " + player1.getColour() + " and his symbol is " + Utility.getWhite());
        System.out.println(player2.getName() + " has colour " + player2.getColour() + " and his symbol is " + Utility.getBlack());
        printBoard();
        for (int i = 1; i <= board.getBoardSize() * board.getBoardSize(); ++i) {
            Player currentPlayer = getCurrentPlayer(i);
            if (currentPlayer instanceof GreedyPlayer) {
                GreedyPlayer.setBoard(board);
                GreedyPlayer.setPrevious(previous);
            }
            Position current = getValidPosition(currentPlayer);
            if (isLastMove(board.getBoardSize(), i) && !isLastMoveConvenient(current, currentPlayer.getColour())) {
                if (currentPlayer.doesNotWantToDoLastMove())
                    break;
            }
            move(current, currentPlayer.getColour());
            printBoard();
        }
        winner();
    }

    /**
     * Prints the {@link Board} in a graphical way.
     */
    public void printBoard() {
        System.out.print(board.toString());
    }

    private Player getCurrentPlayer(int i) {
        return i % 2 == 1 ? player1 : player2;
    }

    private Position getValidPosition(Player currentPlayer) {
        System.out.print(currentPlayer.getName() + " it's your turn!");
        Position current;
        do {
            current = currentPlayer.getPlayerPosition();
        } while (!isMoveValid(current, currentPlayer));
        if (!(currentPlayer instanceof HumanPlayer)) {
            System.out.println(" Moved in " + current.toString());
        }
        return current;
    }

    private boolean isLastMove(int boardSize, int i) {
        return i == boardSize * boardSize;
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
    private boolean isMoveValid(Position current, Player currentPlayer) {
        if (!board.positionIsInsideTheBoard(current)) {
            if (currentPlayer instanceof HumanPlayer)
                System.out.print("The position is not inside the board!");
            return false;
        }
        if (board.stoneIsAlreadyPlacedAt(current)) {
            if (currentPlayer instanceof HumanPlayer)
                System.out.print("The position is already occupied!");
            return false;
        }
        if (anyPositionAdjacentToPreviousOneIsFree()) {
            if (current.isInAdjacentPositions(previous)) {
                return true;
            } else {
                if (currentPlayer instanceof HumanPlayer)
                    System.out.print("The position is not adjacent to the previous one!");
                return false;
            }
        }
        return true;
    }

    @Override
    public void printWinner(int player1LiveStones, int player2LiveStones) {
        if (player1LiveStones > player2LiveStones)
            System.out.println(player1.getName() + " won with " + player1LiveStones + " live stones against " + player2.getName() + "'s " + player2LiveStones);
        else if (player2LiveStones > player1LiveStones)
            System.out.println(player2.getName() + " won with " + player2LiveStones + " live stones against " + player1.getName() + "'s " + player1LiveStones);
        else
            System.out.println("Draw: both players have the same number of live stones: " + player1LiveStones);
    }
}

