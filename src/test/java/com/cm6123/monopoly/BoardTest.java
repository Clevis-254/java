package com.cm6123.monopoly;

import com.cm6123.monopoly.game.Board;
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
        Board b = new Board();

        // Scenario 1: New position is less than or equal to the size of the board
        int diceRoll1 = b.rollDice();
        int oldPosition = b.movePlayer();
        int expectedPosition = oldPosition + diceRoll1;
        int newPosition = b.movePlayer();
        if (expectedPosition > b.getSize()) {
            expectedPosition = expectedPosition % b.getSize();
        }
        assertEquals(expectedPosition, newPosition, "Expected position: " + expectedPosition + ", Actual position: " + newPosition);

        // Scenario 2: New position is greater than the size of the board
        int diceRoll2 = b.rollDice();
        int oldPosition2 = b.movePlayer();
        int expectedPosition2 = oldPosition2 + diceRoll2;
        while (expectedPosition2 > b.getSize()) {
            expectedPosition2 -= b.getSize();
        }
        int newPosition2 = b.movePlayer();
        assertEquals(expectedPosition2, newPosition2, "Expected position: " + expectedPosition2 + ", Actual position: " + newPosition2);
    }



}
