package dssc.project.freedom.players;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HumanPlayerTests {

    Player player = new HumanPlayer("White", Colour.WHITE);

    @Test
    void testGetPlayerPosition() {
        String input = "3 3";
        ByteArrayInputStream source = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        HumanPlayer.setScanner(new Scanner(source));
        assertEquals(Position.at(3, 3), player.getPlayerPosition());
    }

    @Test
    void testPlayerWantsToDoLastMove() {
        String input = "1";
        ByteArrayInputStream source = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        HumanPlayer.setScanner(new Scanner(source));
        assertFalse(player.doesNotWantToDoLastMove());
    }
}
