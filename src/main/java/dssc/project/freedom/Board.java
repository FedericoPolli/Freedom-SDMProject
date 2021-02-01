package dssc.project.freedom;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Stone> board = new HashMap<>();

    public Board(int boardSize) {
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                board.put(Position.at(i, j), Stone.createEmpty());
            }
        }
    }

    public Position positionAt(int x, int y) {
        return board.keySet().stream().filter(p -> p.isAt(x, y)).findAny().orElse(null);
    }

    public Stone getStoneAt(Position p) {
        return board.get(p);
    }

    public void updateStoneAt(Position p, Colour c) {
        board.get(p).makeColoured(c);
    }

    public void setAllStonesDead(){
        board.values().forEach(value -> value.changeLiveStatusTo(false));
    }

    public long countLiveStones(Colour colour) {
        return board.values().stream()
                             .filter(s -> s.isOfColour(colour))
                             .filter(Stone::isLive)
                             .count();
    }

    public boolean areAdjacentPositionOccupied(Position pos){
        return board.keySet().stream()
                .filter(p -> p.isInSurroundingPositions(pos))
                .filter(p -> board.get(p).isOfColour(Colour.NONE))
                .findAny().isEmpty();
    }

    public void check4InDirection(int xDir, int yDir){
        Position previous;
        for (Map.Entry<Position, Stone> entry : board.entrySet()){
            previous = positionAt(entry.getKey().getX() - xDir, entry.getKey().getY() - yDir);
            if (previous != null && isNextPositionOfSameColourAs(previous, entry)) {
                continue;
            }
            int counter = countAdjacentEqualStones(xDir, yDir, entry);
            if (counter == 4){
                setStonesLive(xDir, yDir, entry);
            }
        }
    }

    private void setStonesLive(int xDir, int yDir, Map.Entry<Position, Stone> entry) {
        Position next;
        entry.getValue().changeLiveStatusTo(true);
        for (int i = 1; i < 4; ++i){
            // check message chain entry.getKey().getX()
            next = positionAt(entry.getKey().getX() + i * xDir,
                              entry.getKey().getY() + i * yDir);
            board.get(next).changeLiveStatusTo(true);
        }
    }

    private int countAdjacentEqualStones(int xDir, int yDir, Map.Entry<Position, Stone> entry) {
        int counter = 1;
        Position next;
        for (int i = 1; i < 5; ++i) {
            next = positionAt(entry.getKey().getX() + i * xDir,
                              entry.getKey().getY() + i * yDir);
            if (isNextPositionNotInTheBoard(next))
                break;
            if (isNextPositionOfSameColourAs(next, entry))
                counter++;
            else
                break;
        }
        return counter;
    }

    private boolean isNextPositionOfSameColourAs(Position next, Map.Entry<Position, Stone> entry) {
        return board.get(next).isOfSameColourAs(entry.getValue());
    }

    private boolean isNextPositionNotInTheBoard(Position next) {
        return next == null;
    }

    public void checkBoardAndMakeStonesLive(){
        // Check tiles in horizontal
        check4InDirection(1, 0);
        // Check tiles in vertical
        check4InDirection(0, 1);
        // Check tiles in diagonal
        check4InDirection(1 ,1);
        check4InDirection(-1 , 1);
    }

    public void printBoard() {
        System.out.println((char)254);
        char a = 254;
        System.out.println(a);
    }
}
