package dssc.project.freedom.games;

import dssc.project.freedom.basis.Position;
import org.junit.jupiter.api.Test;

import static dssc.project.freedom.basis.Colour.*;
import static dssc.project.freedom.basis.Position.at;
import static org.junit.jupiter.api.Assertions.*;


public class GameTests {

    private static class ConcreteGame extends Game {

        public ConcreteGame(int boardSize) {
            super(boardSize);
        }
    }

    private ConcreteGame game = new ConcreteGame(5);

    @Test
    void positionValid() {
        Position pos = at(1, 1);
        assertDoesNotThrow(() -> game.isMoveValid(pos));
    }

    @Test
    void positionNotInsideBoard() {
        Position wrongPosition = at(0, 0);
        assertThrows(Exception.class, () -> game.isMoveValid(wrongPosition));
    }

    @Test
    void positionOnAlreadyPlacedStone() {
        Position pos = at(1, 1);
        game.move(pos, WHITE);
        assertThrows(Exception.class, () -> game.isMoveValid(pos));
    }

    @Test
    void positionShouldBeAdjacentToPrevious() {
        Position pos = at(1, 1);
        game.move(pos, WHITE);
        assertAll(
                () -> assertThrows(Exception.class, () -> game.isMoveValid(at(1, 3))),
                () -> assertDoesNotThrow(() -> game.isMoveValid(at(1, 2)))
        );
    }

    @Test
    void whiteWon() {
        game = new ConcreteGame(4);
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= 4; ++j) {
                game.move(at(i, j), j <= 3 ? WHITE : BLACK);
            }
        }
        assertEquals(WHITE, game.winner());
    }

    @Test
    void draw() {
        playGameExceptLastMove(4);
        game.move(at(4, 4), WHITE);
        assertEquals(NONE, game.winner());
    }

    @Test
    void lastMoveNotConvenient() {
        playGameExceptLastMove(5);
        assertFalse(game.isLastMoveConvenient(at(5, 5), WHITE));
    }

    @Test
    void lastMoveConvenient() {
        playGameExceptLastMove(4);
        assertTrue(game.isLastMoveConvenient(at(4, 4), WHITE));
    }

    private void playGameExceptLastMove(int boardSize) {
        game = new ConcreteGame(boardSize);
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                if (i + j == 2 * boardSize)
                    continue;
                game.move(at(i, j), (i + j) % 2 == 0 ? WHITE : BLACK);
            }
        }
    }
}