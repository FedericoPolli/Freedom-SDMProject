package dssc.project.freedom.games;

import dssc.project.freedom.Utility;
import dssc.project.freedom.basis.Board;
import dssc.project.freedom.basis.Position;
import dssc.project.freedom.players.GreedyPlayer;
import dssc.project.freedom.players.HumanPlayer;
import dssc.project.freedom.players.Player;

/**
 * Class that represents a version of the game which can be played from the command line.
 */
public class CommandLineGame extends Game {

    /**
     * The first Player of the game.
     */
    private Player player1;
    /**
     * The second Player of the game.
     */
    private Player player2;

    /**
     * Class constructor. A {@link CommandLineGame} has a {@link Board} on which the Players
     * play, then it has two Players.
     * @param boardSize The size of the Board to be created.
     * @param player1 The first player.
     * @param player2 The second player.
     */
    public CommandLineGame(int boardSize, Player player1, Player player2) throws IllegalArgumentException{
        super(boardSize);
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Represent the actual match of the Game. It prints some information, perform all the turns and declare the winner.
     */
    public void play() {
        printGreetings();
        for (int turn = 1; turn <= board.getBoardSize() * board.getBoardSize(); ++turn) {
            Player currentPlayer = getCurrentPlayer(turn);
            if (currentPlayer instanceof GreedyPlayer) {
                GreedyPlayer.setBoard(board);
                GreedyPlayer.setPrevious(previous);
            }
            Position current = makeTurn(currentPlayer);
            if (isLastMove(board.getBoardSize(), turn) && !isLastMoveConvenient(current, currentPlayer.getColour())) {
                if (currentPlayer.doesNotWantToDoLastMove())
                    break;
            }
            move(current, currentPlayer.getColour());
            printBoard();
        }
        winner();
    }

    /**
     * Prints the names, colours and symbols of the two Players and the initial empty board.
     */
    private void printGreetings() {
        System.out.println(player1.getName() + " has colour " + player1.getColour() + " and his symbol is " + Utility.getWhite());
        System.out.println(player2.getName() + " has colour " + player2.getColour() + " and his symbol is " + Utility.getBlack());
        printBoard();
    }

    /**
     * Prints the {@link Board} in a graphical way.
     */
    public void printBoard() {
        System.out.print(board.toString());
    }

    /**
     * Getter for the {@link Player} who should play in this turn.
     * @param turn Turn of the play.
     * @return The Player who should play in this turn.
     */
    private Player getCurrentPlayer(int turn) {
        return turn % 2 == 1 ? player1 : player2;
    }

    /**
     * Represents the turn of a Player. Advises the Player that is his turn and, if the Player
     * is not human, tell where the Stone has been placed.
     * @param currentPlayer Player whose turn is.
     * @return The Position in which the PFlayer placed his stone.
     */
    private Position makeTurn(Player currentPlayer) {
        System.out.print(currentPlayer.getName() + " it's your turn!");
        Position current = getPositionFromPlayer(currentPlayer);
        if (!(currentPlayer instanceof HumanPlayer)) {
            System.out.println(" Moved in " + current.toString());
        }
        return current;
    }

    /**
     * Gets the Position in which the player want to place the Stone and check if it is valid.
     * If it is not and the Player is human it prints an error message.
     * @param currentPlayer  Player whose turn is.
     * @return The Position in which the PFlayer placed his stone.
     */
    private Position getPositionFromPlayer(Player currentPlayer) {
        Position current;
        do {
            current = currentPlayer.getPlayerPosition();
            try {
                isMoveValid(current);
                break;
            } catch (Exception e) {
                if (currentPlayer instanceof HumanPlayer)
                    System.out.print(e.getMessage());
            }
        } while (true);
        return current;
    }

    /**
     * Checks if it is the last move.
     * @param boardSize Size of the board of this Game.
     * @param turn Turn of the play.
     * @return True if it is the last move, false otherwise.
     */
    private boolean isLastMove(int boardSize, int turn) {
        return turn == boardSize * boardSize;
    }

    /**
     * Prints which Player has won and the scores of both Players.
     * @param player1LiveStones Number of live Stones of the first Player.
     * @param player2LiveStones Number of live Stones of the second Player.
     */
    @Override
    protected void printWinner(int player1LiveStones, int player2LiveStones) {
        if (player1LiveStones > player2LiveStones)
            System.out.println(player1.getName() + " won with " + player1LiveStones + " live stones against " + player2.getName() + "'s " + player2LiveStones);
        else if (player2LiveStones > player1LiveStones)
            System.out.println(player2.getName() + " won with " + player2LiveStones + " live stones against " + player1.getName() + "'s " + player1LiveStones);
        else
            System.out.println("Draw: both players have the same number of live stones: " + player1LiveStones);
    }
}

