package com.cm6123.monopoly.game;


import com.cm6123.monopoly.dice.Dice;

public class Board {
    /**
     * Assigning the board size number.
     */
    private  static final int DEFAULT_BOARD_SIZE = 10;

    /**
     * Assigning the number of faces.
     */
    private static final int DEFAULT_DICE_FACE = 6;
    /**
     * Board contains spaces, it's size is twenty.
     *  Playerposition which is used to loop through the table.
     */
    private int size = DEFAULT_BOARD_SIZE;
    /**
     * Variable contains the spaces.
     */
    private  char[] spaces;
    /**
     * Variable contains the value of the rolled dice.
     */
    private int position;
    /**
     * contains the dice figures.
     */
    private int dice;
    /**
     *  Board constructor.
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
     * @return  size of board.
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
        return dice;
    }
    /**
     * Creating a method that moves the player around the board.
     * @return position returns the position of the player.
     */
    public int  movePlayer() {
        int newPosition = position + dice;
        if (newPosition >= size) {
            newPosition = newPosition % size;
        }
        for (int i = position; i <= newPosition; i++) {
            spaces[i] = 'X';
        }
        position = newPosition;
        System.out.println("The new position is" + position);
        return position;
    }
}

