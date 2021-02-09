package dssc.project.freedom;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static dssc.project.freedom.Colour.BLACK;
import static dssc.project.freedom.Colour.WHITE;
import static dssc.project.freedom.Position.at;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationRunner {

    private ByteArrayOutputStream outputStream;
    private final int boardSize = 4;
    private CommandLineGame commandLineGame = new CommandLineGame(boardSize);

    public ApplicationRunner() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    public void showsMove(String board) {
        assertEquals(board, outputStream.toString());
    }

    public void parseBoard(Position current, Colour colour) {
        commandLineGame.move(current, colour);
        commandLineGame.board.printBoard();
    }

    public void parseWinner() {
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                if (i + j == 2 * boardSize)
                    continue;
                commandLineGame.move(at(i, j), (i + j) % 2 == 0 ? WHITE : BLACK);
            }
        }
        commandLineGame.move(at(4,4), WHITE);
        commandLineGame.winner();
    }
}
