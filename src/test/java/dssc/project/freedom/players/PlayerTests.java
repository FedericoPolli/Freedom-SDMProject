package dssc.project.freedom.players;

import dssc.project.freedom.basis.Colour;
import dssc.project.freedom.basis.Position;
import org.junit.jupiter.api.Test;

import static dssc.project.freedom.basis.Position.at;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {

    private static class TestPlayer extends Player {

        public TestPlayer(String name, Colour colour) {
            super(name, colour);
        }

        @Override
        public Position getPlayerPosition() {
            return at(1, 1);
        }
    }

    private final Player player = new TestPlayer("Player One", Colour.WHITE);

    @Test
    void testGetName() {
        assertEquals("Player One", player.getName());
    }

    @Test
    void testGetColour() {
        assertEquals(Colour.WHITE, player.getColour());
    }

    @Test
    void testChangeColour() {
        player.changeColour(Colour.BLACK);
        assertEquals(Colour.BLACK, player.getColour());
    }
}