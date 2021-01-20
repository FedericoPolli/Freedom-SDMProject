package dssc.project.freedom;

import java.util.HashMap;

public class Board {

    private HashMap<Position, Stone> board = new HashMap<>();
    private final int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                board.put(Position.createAt(i, j), Stone.createEmpty());
            }
        }
    }

    public HashMap<Position, Stone> getBoard() {
        return board;
    }
}
