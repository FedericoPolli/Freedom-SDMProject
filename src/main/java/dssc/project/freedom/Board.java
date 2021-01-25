package dssc.project.freedom;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private Map<Position, Stone> board = new HashMap<>();
    private final int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                board.put(Position.createAt(i, j), Stone.createEmpty());
            }
        }
    }

    public Stone getStoneAt(Position p) {
        return board.get(p);
    }

    public long countLiveStones(Colour colour) {
        return board.values().stream().filter(s -> s.isOfColour(colour)).filter(Stone::isLive).count();
    }

    public void updateStoneAt(Position p, Colour c) {
        board.get(p).makeColoured(c);
    }

    public boolean areAdjacentPositionOccupied(Position pos){
        return board.keySet().stream()
                .filter(p -> p.isInSorroundingPositions(pos))
                .filter(p -> board.get(p).isNotColored())
                .findAny().isEmpty();
    }

    public void check4Horizontal(){
        Position next;
        for (Map.Entry<Position, Stone> entry : board.entrySet()){
            int counter = 1;
            for (int i = 1; i < 5; ++i) {
                next = positionAt(entry.getKey().getX() + i, entry.getKey().getY());
                if (next == null) {
                    break;
                }
                if (board.get(next).isOfColour(entry.getValue().getColour())){
                    counter++;
                } else {
                    break;
                }
            }
            if (counter == 4){
                entry.getValue().makeLive();
                for (int i=1; i<4; ++i){
                    next = positionAt(entry.getKey().getX()+i, entry.getKey().getY());
                    board.get(next).makeLive();
                }
            }
        }
    }

    private Position positionAt(int x, int y) {
        return board.keySet().stream().filter(p -> p.isAt(x, y)).findAny().orElse(null);
    }

}
