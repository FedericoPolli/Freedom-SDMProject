package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

import java.util.Random;

public class RandomPlayer extends Player{

    private final int boardSize;

    public RandomPlayer(String name, Colour colour, int boardSize) {
        super(name, colour);
        this.boardSize = boardSize;
    }

    public Position getPlayerPosition() {
        Random random = new Random();
        int x = random.nextInt(boardSize) +1;
        int y = random.nextInt(boardSize) +1;
        return Position.at(x, y);
    }
}
