package dssc.project.freedom.players;

import dssc.project.freedom.FakeRandomGenerator;
import dssc.project.freedom.RandomGenerator;
import dssc.project.freedom.basis.Colour;
import org.junit.jupiter.api.Test;

import static dssc.project.freedom.basis.Position.at;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomPlayerTests {

    @Test
    public void RandomPosition() {
        RandomGenerator randomGenerator = new FakeRandomGenerator();
        Player player = new RandomPlayer("White", Colour.WHITE, 5, randomGenerator);
        assertAll(
                () -> assertEquals(at(1,2), player.getPlayerPosition()),
                () -> assertEquals(at(3,4), player.getPlayerPosition())
        );
    }
}
