package com.cm6123.monopoly.game;



import java.util.ArrayList;
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
     * contains the selling percentage.
     */
    private static  final int PERCENTAGE = 50;
    /**
     * contains property owned.
     */
    private final ArrayList<Property> propertiesOwned;
    /**
     * player constructor.
     * @param aname contains the name of the player.
     */

    public Player(final String aname) {
        this.name = aname;
        this.position = 1;
        this.balance = 1000;
        this.propertiesOwned = new ArrayList<>();
    }

    /**
     * method that returns player position.
     * @return position returns the position of the player.
     */

    public Integer getPosition() {
        return position;
    }

    /**
     * method that returns player name.
     * @return name  returns the name of the player.
     */

    public String getName() {
        return name;
    }

    /**
     * creating a method that creates an array based on the number of players entered.
     * @param player  is the number of players participating.
     * @return players returns the name of the players.
     */

    public static Player[] createPlayersFromInput(final int player) {
        Scanner s = new Scanner(System.in);
        Player[] players = new Player[player];
        for (int i = 0; i < player; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            String name = s.nextLine();
            players[i] = new Player(name);
        }

        return players;
    }


    /**
     * creating a method that rolles the dice of the player around.
     * @param board is the board class.
     * @return dice is the figure of the rolled dice.
     */

    public int rolledFigure(final Board board) {
        int dice = board.rollDice();
        return dice;
    }

    /**
     * a method that returns the new position of the player.
     * @param dice  is the dice figure.
     * @param board is the board figure.
     * @return newposition returns the player new position.
     */
    public int movePlayer(final Integer dice, final Board board) {
        int size = board.getSize();
        int newPosition = position + dice;
        if (newPosition >= size) {
            newPosition = newPosition % size; // checks whether the new positon is greater than the number of spaces.
            balance += 200; // if so it adds 200 to the balance.
        }
        position = newPosition;
        return position;
    }


    /**
     * creating method that enables player to purchase.
     * @param property is the class property.
     * @return returns the owner of the property.
     */
    public String purchaseProperty(final Property property) {
        String owner = property.getOwner();
        if (owner == null) {
            if (property.getPrice() < balance) {
                int amount = property.getPrice();
                balance -= amount;
                owner = getName();
                property.setOwner(owner);
                add(property);
                logger.info(owner + " has purchased " + property.getName());
            }
        }
        return owner;
    }

    /**
     * adds the property into the property owned list.
     * @param property is the property bought.
     */
    public void add(final Property property){
        propertiesOwned.add(property);
    }

    /**
     * calculates the rent and adds it to balance.
     * @param rent is the rent being paid.
     * @return balance is the balance returned after rent payment.
     */
    public Integer getRent(final int rent){
        balance += rent;
        logger.info(name +" has received rent");
        return balance;
    }
    /**
     * method that prompts the user to pay rent if their exists an owner.
     * @param property is the name of property.
     */
    public void payRent(final Property property) {

        if (property.getOwner() != null) { // checks if their exists an owner.
            int rent = property.getRent();
            balance -= rent; // user pays rent.
            String player = property.getOwner();
            Player owner = new Player(property.getOwner());
            logger.info(name + " has paid rent of " + rent + " to " + owner.getName() + " for " + property.getName());
        }
    }
    /**
     * calculates the balance.
     * @return returns balance.
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * calculates the taxes.
     * @param board  is the board object.
     * @return returns the balance.
     */
    public int payTax(final Board board) {
        int taxAmount = 0;
        if (board.rolledDouble() == true){
            int taxPercent = 5; // set tax percent based on rolledDouble flag
             taxAmount = (int) (balance * (taxPercent / 100.0));
             balance -= taxAmount;
        } else if(board.rolledDouble() == false){
            int taxPercent = 10; // set tax percent based on rolledDouble flag
             taxAmount = (int) (balance * (taxPercent / 100.0));
            balance -= taxAmount;
        }
        System.out.println(taxAmount + "is the amount paid");
        return balance;
    }

    /**
     * calculates the fare of the player if the arrival is at railway station.
     * @return balance returns the balance after fare has been deducted.
     * @param dice  is the dice value.
     */

    public Integer payFare(final Integer  dice) {

        int rolled = dice;
        int fare = (rolled * 10);
        balance -= fare;
        return  balance;
    }
    /**
     * method returns the items owned by owner.
     * @return returns the properties owned by owner.
     */

    public ArrayList<Property> getPropertiesOwned() {
        return propertiesOwned;
    }
    /**
     * counts the number of items rented by owner.
     * @return propertiesOwned size is the number of properties owned by owner.
     */
    public Integer countPropertyOwned(){
        return propertiesOwned.size();
    }
    /**
     * facilitates selling of property.
     * @param property is the property being sold.
     */
    public void sellProperty(final Property property) {
        // Get the property's price
        int price = property.getPrice();
       int percentage = PERCENTAGE;

        // Calculate the amount to be received after deducting the percentage
        int amountReceived = (price * percentage) / 100;

        // Transfer ownership of property to bank
        property.setOwner(null);
        // Add the amount received to the player's balance
        balance += amountReceived;
    }
    /**
     * method that determines whether one is bankrupt or not.
     * if bankrupt and has one property, the property is sold to the banker by 50%.
     * if property is more than one the least valuable property is sold by 50%.
     * @param player  is the player who is bankrupt.
     * @param properties  is the properties of the bankrupt person.
     */
    public void handleBankruptcy(final Player player, final ArrayList<Property> properties) {
         if (player.getBalance() == 0 && player.countPropertyOwned() == 1) {
            Property property = player.getPropertiesOwned().get(0);
            player.sellProperty(property);
        } else if (player.getBalance() == 0) {
            Property leastValuableProperty = properties.get(0);
            for (Property property : player.getPropertiesOwned()) {
                if (property.getPrice() < leastValuableProperty.getPrice()) {
                    leastValuableProperty = property;
                }
            }
            int price = leastValuableProperty.getPrice();
            player.sellProperty(leastValuableProperty);
        }
    }
    /**
     * sets balance to the balance entered.
     * @param abalance is the integer being entered.
     */
    public void setBalance(final int abalance) {
        this.balance = abalance;
    }

}






