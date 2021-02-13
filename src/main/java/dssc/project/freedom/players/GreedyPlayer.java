package dssc.project.freedom.players;

import dssc.project.freedom.Board;
import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

public class GreedyPlayer extends Player{

    private static Board board;
    private static Position previous;

    public static void setPrevious(Position previous) {
        GreedyPlayer.previous = previous;
    }

    public static void setBoard(Board board) {
        GreedyPlayer.board = board;
    }

    public GreedyPlayer(String name, Colour colour) {
        super(name, colour);
    }

    public Position getPlayerPosition() {
        return null;
    }
}
