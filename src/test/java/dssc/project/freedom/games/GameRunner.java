package dssc.project.freedom.games;

import dssc.project.freedom.basis.Position;
import dssc.project.freedom.players.HumanPlayer;
import dssc.project.freedom.players.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static dssc.project.freedom.basis.Colour.*;
import static dssc.project.freedom.basis.Position.at;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRunner {

    private static class FakeCommandLineGame extends CommandLineGame {

        public FakeCommandLineGame(int boardSize, Player player1, Player player2) {
            super(boardSize, player1, player2);
        }

        @Override
        public void playGame() {
            int boardSize = board.getBoardSize();
            for (int turn = 1; turn <= boardSize * boardSize; ++turn) {
                Player currentPlayer = getCurrentPlayer(turn);
                Position current = playATurn(currentPlayer);
                if (isLastMove(boardSize, turn) && !isLastMoveConvenient(current, currentPlayer.getColour()))
                    if (currentPlayer.doesNotWantToDoLastMove())
                        break;
                move(current, currentPlayer.getColour());
            }
        }
    }

    private final ByteArrayOutputStream outputStream;
    private final int boardSize;
    private final FakeCommandLineGame commandLineGame;

    public GameRunner(int boardSize) {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        this.boardSize = boardSize;
        Player player1 = new HumanPlayer("White", WHITE);
        Player player2 = new HumanPlayer("Black", BLACK);
        this.commandLineGame = new FakeCommandLineGame(boardSize, player1, player2);
    }

    public void testOutput(String expectedOutput) {
        assertEquals(expectedOutput, outputStream.toString());
    }

    public void testWinner(String output) {
        for (int x = 1; x <= boardSize; ++x) {
            for (int y = 1; y <= boardSize; ++y) {
                commandLineGame.move(at(x, y), (x * y) % 2 == 0 ? WHITE : BLACK);
            }
        }
        commandLineGame.winner();
        testOutput(output);
    }

    public void testDraw(String output) {
        for (int i = 1; i <= boardSize; ++i) {
            for (int j = 1; j <= boardSize; ++j) {
                commandLineGame.move(at(i, j), (i + j) % 2 == 0 ? WHITE : BLACK);
            }
        }
        commandLineGame.winner();
        testOutput(output);
    }

    public void testPlay(String input, String output) {
        ByteArrayInputStream source = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        HumanPlayer.setScanner(new Scanner(source));
        commandLineGame.playGame();
        testOutput(output);
    }

    public void testGreetings(String output) {
        commandLineGame.printGreetings();
        testOutput(output);
    }
}