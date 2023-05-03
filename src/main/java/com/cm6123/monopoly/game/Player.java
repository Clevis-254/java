package com.cm6123.monopoly.game;



import java.util.Scanner;

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
     *
     * @param aname contains the name of the player.
     */
    public Player(final String aname) {
        this.name = aname;
        this.position = 1;
        this.balance = 1000;
    }

    /**
     * method that returns player position.
     *
     * @return position returns the position of the player.
     */

    public Integer getPosition() {
        return position;
    }

    /**
     * method that returns player name.
     *
     * @return name  returns the name of the player.
     */

    public String getName() {
        return name;
    }

    /**
     * creating a method that creates an array based on the number of players entered.
     * @return players returns the name of the players.
     */

    public static Player[] createPlayersFromInput() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int player = s.nextInt();
        while (player < 2 || player > 10) {
            System.out.println("Number of players must be between 2 and 10. Please enter again:");
            player = s.nextInt();
        }
        s.nextLine(); // consume the newline character

        Player[] players = new Player[player];

        for (int i = 0; i < player; i++) {
            System.out.print("Enter name for player " + (i+1) + ": ");
            String name = s.nextLine();
            players[i] = new Player(name);
        }

        return players;
    }



    /**
     * creating a method that rolles the dice of the player around.
     *
     * @param board is the board class.
     * @return dice is the figure of the rolled dice.
     */

    public int rolledFigure(final Board board) {
        int dice = board.rollDice();
        return dice;
    }

    /**
     * a method that returns the new position of the player.
     *
     * @param dice  is the dice figure.
     * @param board is the board figure.
     * @return newposition returns the player new position.
     */
    public int movePlayer(final Integer dice, final Board board) {
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
     * creating method that enables player to purchase.
     *
     * @param property is the class property.
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
            System.out.println("Property is owned by" + owner);
        }

        return owner;
    }



    /**
     * method that prompts the user to pay rent if their exists an owner.
     *
     * @param property is the name of property.
     */
    public void payRent(final Property property) {

        if (property.getOwner() != null) {
            int rent = property.getRent();
            balance -= rent;
            String player = property.getOwner();
            Player owner = new Player(property.getOwner());
            owner.balance += rent ;
            logger.info(name + " has paid rent of " + rent + " to " + owner.getName() + " for " + property.getName());
        }
    }


    /**
     * calculates the balance.
     *
     * @return returns balance.
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * calculates the taxes.
     * @param facility is the facility name.
     * @param aposition is the positon of the player.
     * @return returns the balance.
     */
    public int payTax(final Facility facility, final int aposition) {
        if (facility.getPosition() == position){
            if (facility.getName().equals("Tax office")){
                int taxPercent = 10; // set tax percent based on rolledDouble flag
                int taxAmount = (int) (balance * (taxPercent / 100.0));
                balance = balance - taxAmount;
            }
        }
        return balance;
    }

}






