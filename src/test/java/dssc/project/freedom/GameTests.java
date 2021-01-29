package dssc.project.freedom;

import static dssc.project.freedom.Position.createAt;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class GameTests {
    private Game game;

    @Test
    public void positionNotInsideBoard() {
        game = new Game(5);
        Position wrongPosition = createAt(0, 0);
        assertFalse(game.isMoveValid(wrongPosition));
    }

    @Test
    public void positionOnAlreadyPlacedStone() {
        game = new Game(5);
        Position pos = createAt(1, 1);
        game.play(pos, Colour.WHITE);
        assertFalse(game.isMoveValid(pos));
    }

    @Test
    public void positionValid() {
        game = new Game(5);
        Position pos = createAt(1, 1);
        assertTrue(game.isMoveValid(pos));
    }

    @Test
    public void positionNotAdjacentToPrevious() {
        game = new Game(5);
        Position pos = createAt(1, 1);
        game.play(pos, Colour.WHITE);
        assertFalse(game.isMoveValid(createAt(1, 3)));
    }

    @Test
    public void positionAdjacentToPrevious() {
        game = new Game(5);
        Position pos = createAt(1, 1);
        game.play(pos, Colour.WHITE);
        assertTrue(game.isMoveValid(createAt(1, 2)));
    }

    @Test
    public void whiteWon() {
        game = new Game(4);
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                if (j <= 3)
                    game.play(createAt(i,j), Colour.WHITE);
                else
                    game.play(createAt(i,j), Colour.BLACK);
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
                    game.play(createAt(i, j), Colour.WHITE);
                } else {
                    game.play(createAt(i, j), Colour.BLACK);
                }
            }
        }
        assertEquals(Colour.NONE, game.winner());
    }

    public void lastMove() {
        
    }
}
