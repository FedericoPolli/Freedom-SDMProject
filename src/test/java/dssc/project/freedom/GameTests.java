package dssc.project.freedom;

import static dssc.project.freedom.Position.at;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class GameTests {
    private Game game;

    @Test
    public void positionNotInsideBoard() {
        game = new Game(5);
        Position wrongPosition = at(0, 0);
        assertFalse(game.isMoveValid(wrongPosition));
    }

    @Test
    public void positionOnAlreadyPlacedStone() {
        game = new Game(5);
        Position pos = at(1, 1);
        game.move(pos, Colour.WHITE);
        assertFalse(game.isMoveValid(pos));
    }

    @Test
    public void positionValid() {
        game = new Game(5);
        Position pos = at(1, 1);
        assertTrue(game.isMoveValid(pos));
    }

    @Test
    public void positionNotAdjacentToPrevious() {
        game = new Game(5);
        Position pos = at(1, 1);
        game.move(pos, Colour.WHITE);
        assertFalse(game.isMoveValid(at(1, 3)));
    }

    @Test
    public void positionAdjacentToPrevious() {
        game = new Game(5);
        Position pos = at(1, 1);
        game.move(pos, Colour.WHITE);
        assertTrue(game.isMoveValid(at(1, 2)));
    }

    @Test
    public void whiteWon() {
        game = new Game(4);
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                if (j <= 3)
                    game.move(at(i,j), Colour.WHITE);
                else
                    game.move(at(i,j), Colour.BLACK);
            }
        }
        assertEquals(Colour.WHITE, game.winner());
    }

    @Test
    public void draw() {
        game = new Game(4);
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                if((i + j) % 2 == 0) {
                    game.move(at(i, j), Colour.WHITE);
                } else {
                    game.move(at(i, j), Colour.BLACK);
                }
            }
        }
        assertEquals(Colour.NONE, game.winner());
    }

    @Test
    public void lastMoveNotConvenient(){
        game = new Game(5);
        for (int i = 1; i <= 5; ++i) {
            for (int j = 1; j <= 5; ++j) {
                if((i + j) == 10){
                    continue;
                }
                if((i + j) % 2 == 0) {
                    game.move(at(i, j), Colour.WHITE);
                } else {
                    game.move(at(i, j), Colour.BLACK);
                }
            }
        }
        assertFalse(game.isLastMoveConvenient(at(5,5), Colour.WHITE));
    }

    @Test
    public void lastMoveConvenient(){
        game = new Game(4);
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                if((i + j) == 8){
                    continue;
                }
                if((i + j) % 2 == 0) {
                    game.move(at(i, j), Colour.WHITE);
                } else {
                    game.move(at(i, j), Colour.BLACK);
                }
            }
        }
        assertTrue(game.isLastMoveConvenient(at(4,4), Colour.WHITE));
    }
}
