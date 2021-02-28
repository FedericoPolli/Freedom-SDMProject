package dssc.project.freedom.games;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;
import dssc.project.freedom.players.HumanPlayer;
import dssc.project.freedom.players.Player;
import dssc.project.freedom.utilities.Utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static dssc.project.freedom.basis.Colour.*;
import static dssc.project.freedom.basis.Position.at;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRunner {

    private final ByteArrayOutputStream outputStream;
    private final int boardSize;
    private final CommandLineGame commandLineGame;

    public GameRunner(int boardSize) {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        this.boardSize = boardSize;
        Player player1 = new HumanPlayer("White", WHITE);
        Player player2 = new HumanPlayer("Black", BLACK);
        this.commandLineGame = new CommandLineGame(boardSize, player1, player2);
    }

    public void testOutput(String expectedOutput) {
        assertEquals(expectedOutput, outputStream.toString());
    }

    public void testOutputWithoutBoardPrints(String expectedOutput) {
        String output = outputStream.toString();
        StringBuffer text = new StringBuffer(output);
        int boardLength = (4 * boardSize + 4) * (2 * boardSize + 2);
        if (Utility.getOS().equals("Windows"))
            boardLength += boardSize * 2 + 2;
        while (output.contains("+")) {
            int i = text.indexOf("+") - 2;
            text = text.replace(i, i + boardLength, "");
            output = text.toString();
        }
        assertEquals(expectedOutput, output);
    }

    public void parseBoard(List<Position> positions, List<Colour> colours) {
        for (int i = 0; i < positions.size(); ++i) {
            commandLineGame.move(positions.get(i), colours.get(i));
        }
        commandLineGame.printBoard();
    }

    public void parseWinner() {
        for (int x = 1; x <= boardSize; ++x) {
            for (int y = 1; y <= boardSize; ++y) {
                commandLineGame.move(at(x, y), (x * y) % 2 == 0 ? WHITE : BLACK);
            }
        }
        commandLineGame.winner();
    }

    public void parseDraw() {
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                commandLineGame.move(at(i, j), (i + j) % 2 == 0 ? WHITE : BLACK);
            }
        }
        commandLineGame.winner();
    }

    public void parsePlay(String input) {
        ByteArrayInputStream source = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        HumanPlayer.setScanner(new Scanner(source));
        commandLineGame.playGame();
    }
}