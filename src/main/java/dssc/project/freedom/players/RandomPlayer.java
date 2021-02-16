package dssc.project.freedom.players;

import dssc.project.freedom.Colour;
import dssc.project.freedom.Position;

import static dssc.project.freedom.Utility.getRandomInteger;

public class RandomPlayer extends Player{

    private final int boardSize;

    public RandomPlayer(String name, Colour colour, int boardSize) {
        super(name, colour);
        this.boardSize = boardSize;
    }

    public Position getPlayerPosition() {
        return Position.at(getRandomInteger(boardSize) + 1, getRandomInteger(boardSize) + 1);
    }
}
