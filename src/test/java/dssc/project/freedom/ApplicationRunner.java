package dssc.project.freedom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationRunner {

    private final ByteArrayOutputStream outputStream;

    public ApplicationRunner() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    public void parseBoardSize(String input, String expectedOutput, int expectedValue) {
        Scanner in = new Scanner(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8)));
        GameHandler gameHandler = new GameHandler(in);
        int value = gameHandler.getBoardSize();
        assertAll(
                () -> assertEquals(expectedOutput, outputStream.toString()),
                () -> assertEquals(expectedValue, value)
        );
    }

    public void parseTypeOfPlayer(String input, String expectedOutput, char expectedValue) {
        Scanner in = new Scanner(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8)));
        GameHandler gameHandler = new GameHandler(in);
        int value = gameHandler.getTypeOfPlayer();
        assertAll(
                () -> assertEquals(expectedOutput, outputStream.toString()),
                () -> assertEquals(expectedValue, value)
        );
    }
}