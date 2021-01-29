package dssc.project.freedom;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private Map<Position, Stone> board = new HashMap<>();

    public Board(int boardSize) {
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
                .filter(p -> p.isInSurroundingPositions(pos))
                .filter(p -> board.get(p).isNotColored())
                .findAny().isEmpty();
    }

    public void check4(int xDirection, int yDirection){
        Position next, previous;
        for (Map.Entry<Position, Stone> entry : board.entrySet()){
            int counter = 1;
            previous = positionAt(entry.getKey().getX() - xDirection, entry.getKey().getY() - yDirection);
            if (previous != null && board.get(previous).isOfColour(entry.getValue().getColour())) {
                continue;
            }
            for (int i = 1; i < 5; ++i) {
                next = positionAt(entry.getKey().getX() + i * xDirection,
                                  entry.getKey().getY() + i * yDirection);
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
                    next = positionAt(entry.getKey().getX() + i * xDirection,
                                      entry.getKey().getY() + i * yDirection);
                    board.get(next).makeLive();
                }
            }
        }
    }

    public void checkAllDirections(){
        // Check tiles in horizontal
        check4(1, 0);
        // Check tiles in vertical
        check4(0, 1);
        // Check tiles in diagonal
        check4(1 ,1);
        check4(-1 , 1);
    }

    public Position positionAt(int x, int y) {
        return board.keySet().stream().filter(p -> p.isAt(x, y)).findAny().orElse(null);
    }

    public void printBoard() {
        System.out.println(board.toString());
    }



}
