package com.cm6123.monopoly.game;

import java.util.ArrayList;

public class Player {
    /**
     * String that contains player names.
     */
    private final String name;
    /**
     * contains the current position of the player.
     */
    private final Integer position;
    /**
     * player constructor.
     * @param aname contains the name of the player.
     */

    public Player(final String aname){
        this.name = aname;
        this.position = 1;
    }
    /**
     * method that returns player position.
     * @return  position returns the position of the player.
     */

    public Integer getPosition(){
        return position;
    }
    /**
     * method that returns player name.
     * @return name  returns the name of the player.
     */

    public String getName(){
        return name;
    }
    /**
     * creating a method that creates an array based on the number of players entered.
     * @param numPlayers contains the number of players.
     * @param playerNames contains the name of players.
     * @return players returns the name of the players.
     */

    public static Player[] createPlayers(final int numPlayers, final String[] playerNames) {
        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(playerNames[i]);
        }

        return players;
    }
    /**
     * creating a method that rolles the dice of the player around.
     * @return  dice is the figure of the rolled dice.
     * @param board  is the board class.
     */

    public int rolledFigure(final Board board) {
        int dice = board.rollDice();
        return dice;
    }

    /**
     * a method that returns the new position of the player.
     * @param dice is the dice figure.
     * @param board is the board figure.
     * @return newposition returns the player new position.
     */
    public int movePlayer(final Integer dice,final Board board){
        int size = board.getSize();
        int newPosition = position + dice;
        if (newPosition >= size) {
            newPosition = newPosition % size;
        }
        return newPosition;
    }

    /**
     * method that returns the name of the property one is.
     * @param properties represent the array list that has all the properties.
     * @param newPosition represent the newposition
     */
    public void displayProperties(final ArrayList<Property> properties,final int newPosition){
        // Check if the player has landed on a property
        for (Property p : properties) {
            if (newPosition == p.getPosition()) {
                 System.out.println("You have landed on the " + p.getName());
            }
        }
    }
    /**
     * method that returns facility if found by the user.
     *
     */

    public void displayFacilities(final ArrayList<Facility> facilities,final int newPosition){
        // checks if the player has landed on facility
        for (Facility f : facilities){
            if(newPosition == f.getPosition()){
                System.out.println("You have landed on the " + f.getName());
            }
        }

    }
    public static void addPlayers(final ArrayList<Player> players) {
        String[] names = {"james","Jane","Paul"};

        for (int i = 0; i < names.length; i++) {
            Player p = new Player(names[i]);
            players.add(p);
        }
    }

}




