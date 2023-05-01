package com.cm6123.monopoly;

import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.Property;
import org.junit.jupiter.api.Test;
import com.cm6123.monopoly.dice.Dice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class BoardTest {
    @Test
    public void testMovePlayer() {
        Player  p = new Player("james");
        Player z = new Player("john");
        Board b = new Board();
        // Scenario 1: New position is less than or equal to the size of the board
        int oldPosition = p.getPosition();
        int diceRoll1 = p.rolledFigure(b);
        int newPosition = p.movePlayer(diceRoll1,b);
        int expectedPosition = oldPosition + diceRoll1;
        if (expectedPosition > b.getSize()) {
            expectedPosition = expectedPosition % b.getSize();
        }
        assertEquals(expectedPosition, newPosition, "Expected position1: " + expectedPosition + ", Actual position1: " + newPosition);

        // Scenario 2: New position is greater than the size of the board

        int oldPosition2 = z.getPosition();
        int rolledFigure2 = z.rolledFigure(b);
        int expectedPosition2 = oldPosition2 + rolledFigure2;
        while (expectedPosition2 > b.getSize()) {
            expectedPosition2 -= b.getSize();
        }
        int newPosition2 = z.movePlayer(rolledFigure2,new Board());
        assertEquals(expectedPosition2, newPosition2, "Expected position2: " + expectedPosition2 + ", Actual position2: " + newPosition2);


    }

}
