package dssc.project.freedom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static dssc.project.freedom.Colour.BLACK;
import static dssc.project.freedom.Colour.WHITE;
import static dssc.project.freedom.Position.at;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationRunner {

    private ByteArrayOutputStream outputStream;
    private final int boardSize;
    private CommandLineGame commandLineGame;

    public ApplicationRunner(int boardSize) {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        this.boardSize = boardSize;
        this.commandLineGame = new CommandLineGame(boardSize);
    }

    public void testOutput(String expectedOutput) {
        assertEquals(expectedOutput, outputStream.toString());
    }

    public void testOutput2(String expectedOutput) {
        int a = outputStream.toString().indexOf("+");
        System.err.println(a);
        System.err.println(outputStream.toString().substring(a, a+50));
        assertEquals(expectedOutput, outputStream.toString());
    }

    public void parseBoard(Position current, Colour colour) {
        commandLineGame.move(current, colour);
        commandLineGame.board.printBoard();
    }

    public void parseWinner() {
        for(int x = 1; x <= boardSize; ++x) {
            for(int y = 1; y <= boardSize; ++y) {
                commandLineGame.move(at(x, y), (x * y) % 2 == 0 ? WHITE : BLACK);
            }
        }
        commandLineGame.winner();
    }

    public void parseDraw() {
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

    public void parsePlay(String input) {
        commandLineGame.play(new Scanner(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8))));
    }
}
