package dssc.project.freedom.players;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanPlayerTests {

    Player player = new HumanPlayer("White", Colour.WHITE);

    @Test
    void testGetPlayerPosition() {
        String input = "3 3";
        ByteArrayInputStream source = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        HumanPlayer.setScanner(new Scanner(source));
        assertEquals(Position.at(3, 3), player.getPlayerPosition());
    }

    @ParameterizedTest
    @CsvSource({"1, false", "0, true"})
    void testIfPlayerWantsToDoLastMove(String input, boolean expected) {
        ByteArrayInputStream source = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        HumanPlayer.setScanner(new Scanner(source));
        assertEquals(expected, player.doesNotWantToDoLastMove());
    }
}
