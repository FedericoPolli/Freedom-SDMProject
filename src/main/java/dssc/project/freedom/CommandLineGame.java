package dssc.project.freedom;

import dssc.project.freedom.players.GreedyPlayer;
import dssc.project.freedom.players.HumanPlayer;
import dssc.project.freedom.players.Player;
import dssc.project.freedom.players.RandomPlayer;

public class CommandLineGame extends Game{

    Player player1;
    Player player2;

    /**
     * Class constructor. A {@link Game} has a {@link Board} on which the players play.
     *
     * @param boardSize The size of the Board to be created.
     */
    public CommandLineGame(int boardSize, char player1, char player2) {
        super(boardSize);
        switch (player1) {
            case 'h' -> this.player1 = new HumanPlayer("White", Colour.WHITE);
            case 'r' -> this.player1 = new RandomPlayer("White", Colour.WHITE, boardSize);
            case 'g' -> this.player1 = new GreedyPlayer("White", Colour.WHITE);
        }
        switch (player2) {
            case 'h' -> this.player2 = new HumanPlayer("Black", Colour.BLACK);
            case 'r' -> this.player2 = new RandomPlayer("Black", Colour.BLACK, boardSize);
            case 'g' -> this.player2 = new GreedyPlayer("Black", Colour.BLACK);
        }
    }

    public void play() {
        for (int i = 1; i <= board.boardSize * board.boardSize; ++i){
            Player currentPlayer = getCurrentPlayer(i);
            if (currentPlayer instanceof GreedyPlayer) {
                GreedyPlayer.setBoard(board);
                GreedyPlayer.setPrevious(previous);
            }
            Position current = getValidPosition(currentPlayer);
            if (isLastMove(board.boardSize, i) && !isLastMoveConvenient(current, currentPlayer.getColour())){
                if (currentPlayer.doesNotWantToDoLastMove())
                    break;
            }
            move(current, currentPlayer.getColour());
            board.printBoard();
        }
        winner();
    }

    private Player getCurrentPlayer(int i){
        return i % 2 == 1 ? player1 : player2;
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

    private boolean isLastMove(int boardSize, int i) {
        return i == boardSize * boardSize;
    }

    private Position getValidPosition(Player currentPlayer) {
        System.out.print(currentPlayer.getName() + " it's your turn!");
        Position current;
        do {
            current = currentPlayer.getPlayerPosition();
        } while (!isMoveValid(current, currentPlayer));
        if (!(currentPlayer instanceof HumanPlayer)){
            System.out.println();
        }
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
    public boolean isMoveValid(Position current, Player currentPlayer) {
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

}

