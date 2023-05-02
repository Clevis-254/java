package com.cm6123.monopoly.game;

import java.util.ArrayList;

import static com.cm6123.monopoly.app.Application.logger;


public class Player {
    /**
     * String that contains player names.
     */
    private final String name;
    /**
     * contains the current position of the player.
     */
    private Integer position;
    /**
     * contains the balance of the player.
     */
    private Integer balance;
    /**
     * player constructor.
     * @param aname contains the name of the player.
     */
    public Player(final String aname){
        this.name = aname;
        this.position = 1;
        this.balance = 1000;
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
            balance += 200;
        }
        position = newPosition;
        return position;
    }

    /**
     * method that returns the name of the property one is.
     * @param properties represent the array list that has all the properties.
     * @param newPosition represent the newposition
     * @return propertyname returns the name of the property.
     */
    public String displayProperties(final ArrayList<Property> properties,final int newPosition){
        // Check if the player has landed on a property
        String propertyname = null;
        for (Property p : properties) {
            if (newPosition == p.getPosition()) {
                propertyname = p.getName();
                 System.out.println("You have landed on" + p.getName());
            }
        }

        return propertyname;
    }
    /**
     * method that returns facility if found by the user.
     * @return should return the facility name.
     * @param newPosition is the new position of the player.
     * @param facilities  is the array of the facilities available.
     */

    public String displayFacilities(final ArrayList<Facility> facilities,final int newPosition) {
        // checks if the player has landed on facility
        String facilityname = null;
        for (Facility f : facilities) {
            if (newPosition == f.getPosition()) {
                facilityname = f.getName();
                System.out.println("You have landed on the " + f.getName());
            }
        }
        return facilityname;
    }

    /**
     * adds the players in list form.
     * @param players is the array of players being added.
     */
    public static void addPlayers(final ArrayList<Player> players) {
        String[] names = {"james","Jane","Paul"};

        for (int i = 0; i < names.length; i++) {
            Player p = new Player(names[i]);
            players.add(p);
        }
    }

    /**
     * calculates the balance.
     * @return returns balance.
     */
    public Integer getBalance(){
        return balance;
    }
    /**
     * creating method that enables player to purchase.
     * @param property  is the class property.
     * @return returns the owner of the property.
     */
    public String purchaseProperty(final Property property) {
        String owner = property.getOwner();
        if (property.getOwner() == null && balance > property.getPrice()) {
            balance -= property.getPrice();
            String newOwner = property.setOwner(name);
            owner = newOwner;
            logger.info(name + " has purchased " + property.getName() + " for " + property.getPrice());
        } else if (property.getOwner() == null && balance < property.getPrice()) {
            System.out.println("Not enough balance available");
        } else {
            System.out.println("Property is owned" + owner);
        }

        return owner;
    }


}




