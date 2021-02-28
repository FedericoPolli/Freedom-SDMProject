package dssc.project.freedom;

import dssc.project.freedom.basis.Direction;
import dssc.project.freedom.basis.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static dssc.project.freedom.basis.Position.at;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTests {

    private Position position = new Position(3,3);

    @ParameterizedTest
    @CsvSource({"5, 3, RIGHT", "1, 3, LEFT", "3, 5, UP", "3, 1, DOWN"})
    void checkMoveInDirectionWithStepTwo(int expectedX, int expectexY, Direction direction){
        assertEquals(at(expectedX,expectexY), position.moveInDirectionWithStep(direction, 2));
    }

    @ParameterizedTest
    @CsvSource({"2, 2", "4, 3", "3, 4"})
    void checkIsInAdjacentPositions(int x, int y){
        assertTrue(position.isInAdjacentPositions(at(x,y)));
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "1, 3", "3, 5"})
    void checkNotInAdjacentPositions(int x, int y){
        assertFalse(position.isInAdjacentPositions(at(x,y)));
    }
}
