package dssc.project.freedom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationRunner {

    private ByteArrayOutputStream outputStream;
    private CommandLineGame game = new CommandLineGame(4);

    public ApplicationRunner() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
/*
    public void parseFile(Path filePath) throws IOException {
        Main.main(filePath.toString());
    }*/

    public void showsMove(String board) {
        assertEquals(board, outputStream.toString());
    }

    public void parseMove(Position current, Colour colour) {
        game.move(current, colour);
    }
}
