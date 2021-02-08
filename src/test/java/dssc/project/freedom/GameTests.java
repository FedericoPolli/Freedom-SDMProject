package dssc.project.freedom;

import static dssc.project.freedom.Colour.*;
import static dssc.project.freedom.Position.at;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class GameTests {

    private Game game;

    @Test
    public void positionValid() {
        game = new Game(5);
        Position pos = at(1, 1);
        assertTrue(game.isMoveValid(pos));
    }

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
        game.move(pos, WHITE);
        assertFalse(game.isMoveValid(pos));
    }

    @ParameterizedTest
    @CsvSource({"3, false", "2, true"})
    public void positionShouldBeAdjacentToPrevious(int y, boolean expected) {
        game = new Game(5);
        Position pos = at(1, 1);
        game.move(pos, WHITE);
        assertEquals(expected, game.isMoveValid(at(1, y)));
    }

    @Test
    public void whiteWon() {
        game = new Game(4);
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                game.move(at(i,j), j <= 3 ? WHITE : BLACK);
            }
        }
        assertEquals(WHITE, game.winner());
    }

    private void playGame(int boardSize) {
        game = new Game(boardSize);
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                if (i + j == 2 * boardSize)
                    continue;
                game.move(at(i, j), (i + j) % 2 == 0 ? WHITE : BLACK);
            }
        }
    }

    @Test
    public void draw() {
        playGame(4);
        game.move(at(4,4), WHITE);
        assertEquals(Colour.NONE, game.winner());
    }

    @Test
    public void lastMoveNotConvenient(){
        playGame(5);
        assertFalse(game.isLastMoveConvenient(at(5,5), WHITE));
    }

    @Test
    public void lastMoveConvenient(){
        playGame(4);
        assertTrue(game.isLastMoveConvenient(at(4,4), WHITE));
    }
}
