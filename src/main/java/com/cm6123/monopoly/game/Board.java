package com.cm6123.monopoly.game;

import com.cm6123.monopoly.dice.Dice;

public class Board {
    /**
     * Assigning the board size number.
     */
    private static final int DEFAULT_BOARD_SIZE = 15;

    /**
     * Assigning the number of faces.
     */
    private static final int DEFAULT_DICE_FACE = 6;
    /**
     * Board contains spaces, it's size is twenty.
     * Playerposition which is used to loop through the table.
     */
    private final int size = DEFAULT_BOARD_SIZE;
    /**
     * Variable contains the spaces.
     */
    private final char[] spaces;
    /**
     * Variable contains the value of the rolled dice.
     */
    private final int position;
    /**
     * contains the dice figures.
     */
    private int dice;
    /**
     * Board constructor.
     */
    public Board() {
        spaces = new char[size];
        for (int i = 0; i < size; i++) {
            spaces[i] = ' ';
        }
        position = 1;
    }

    /**
     * board size method.
     * @return size of board.
     */
    public int getSize(){
        return size;
    }
    /**
     * calculates the number of position one is supposed to move.
     * @return dice returns the total calculation of dices.
     */
    public int rollDice() {
        Dice n = new Dice(DEFAULT_DICE_FACE);
        int roll1 = n.roll();
        int roll2 = n.roll();
        dice = roll1 + roll2;

        boolean rolledDouble;
        if (roll1 == roll2) {
            rolledDouble = true;
        } else {
            rolledDouble = false;
        }

        return dice;
    }


}
