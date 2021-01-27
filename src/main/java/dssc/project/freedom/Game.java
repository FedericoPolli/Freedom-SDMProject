package dssc.project.freedom;

import java.util.Scanner;

public class Game {

    private Board board;
    private final int boardSize;
    private int numberOfStonesPlaced = 0;

    public Game(int boardSize) {
        this.boardSize = boardSize;
        this.board = new Board(boardSize);
    }

    public void play() {
        Scanner in = new Scanner(System.in);

        int x, y;
        Position current, previous = null;
        for(int i = 0; i < boardSize * boardSize; ++i) {
            System.out.println("Enter the position of the stone:");
            x = in.nextInt();
            y = in.nextInt();
            current = Position.createAt(x, y);
            // check if the position is inside the board
            // check if the stone is already coloured
            while (!board.getStoneAt(current).isNotColored()) {
                System.out.println("The position is already taken, insert a new position:");
                x = in.nextInt();
                y = in.nextInt();
                current = Position.createAt(x, y);
            }
            if (previous != null) {
                // check if the new stone is adjacent to the previous one
                board.areAdjacentPositionOccupied(previous);
            }
            if (i % 2 == 0) {
                board.updateStoneAt(board.positionAt(x, y), Colour.WHITE);
            } else {
                board.updateStoneAt(Position.createAt(x, y), Colour.BLACK);
            }
            previous = current;
        }
    }
}
