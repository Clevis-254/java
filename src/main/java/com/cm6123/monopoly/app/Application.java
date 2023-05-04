package com.cm6123.monopoly.app;


import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Facility;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Scanner;

import static com.cm6123.monopoly.game.Facility.displayFacilities;
import static com.cm6123.monopoly.game.Player.createPlayersFromInput;
import static com.cm6123.monopoly.game.Property.addProperty;
import static com.cm6123.monopoly.game.Property.displayProperties;

public final class Application {
    /**
     * Create a logger for the class.
     */
    public static final Logger logger = LoggerFactory.getLogger(Application.class);
    /**
     * name shows the number of players playing the game.
     */
    private int players;


    private Application() {
        // Create players
        Player[] players = createPlayersFromInput();

        // Create board, properties and facilities
        Board board = new Board();
        ArrayList<Property> properties = new ArrayList<>();
        addProperty(properties);
        ArrayList<Facility> facilities = new ArrayList<>();
        Facility.addFacilities(facilities);

        // Game loop
        boolean gameOver = false;
        int currentPlayerIndex = 0;

        while (!gameOver) {
            // Get current player
            Player currentPlayer = players[currentPlayerIndex];

            // Roll the dice
            int diceRoll = currentPlayer.rolledFigure(board);
            System.out.println(currentPlayer.getName() + " has rolled a " + diceRoll);

            // Move the player
            int position = currentPlayer.movePlayer(diceRoll, board);

            // Display the location
            // Display the location
            Property currentProperty = displayProperties(properties, position);
            Facility currentFacility = displayFacilities(facilities, position);
            if (currentProperty != null) {
                currentProperty.getName();
                if (currentProperty.getOwner() != null) {
                    currentPlayer.payRent(currentProperty);
                    int rent = currentProperty.getRent();
                    Player owner = new Player(currentProperty.getOwner());
                    owner.getRent(rent);
                    System.out.println("You have paid a rent to the owner, your new balance is " + currentPlayer.getBalance());
                } else if (currentProperty.getOwner() == null) {
                    if (currentPlayer.getBalance() < currentProperty.getPrice()) {
                        System.out.println("Cannot purchase property as you have less balance");
                    } else {
                        Scanner s = new Scanner(System.in);
                        System.out.println("Do you wish to purchase property? (yes/no)");
                        String answer = s.nextLine();
                        if (answer.equalsIgnoreCase("Yes")) {
                            currentPlayer.purchaseProperty(currentProperty);
                        }
                    }
                }
            }

            if (currentFacility != null) {
                currentFacility.getName();
                if (currentFacility.getName() == "Tax-office") {
                    currentPlayer.payTax();
                    System.out.println("You have paid your taxes therefore your balance is" + currentPlayer.getBalance());
                } else if (currentFacility.getName() == "Station") {
                    currentPlayer.payFare(diceRoll);
                    System.out.println("You have paid your fare therefore your balance is" + currentPlayer.getBalance());
                }
            }

            // Handle bankruptcy
            if (currentPlayer.getBalance()== 0 && currentPlayer.countPropertyOwned() == 0) {
                // Remove player from the game
                System.out.println(currentPlayer.getName() + " is out of the game.");
                players[currentPlayerIndex] = null;
            } else if ((currentPlayer.getBalance() ==  0 && currentPlayer.countPropertyOwned() >0)) {
                currentPlayer.handleBankruptcy(currentPlayer,properties);
            }
            // Check if game is over
            int playersLeft = 0;
            for (Player player : players) {
                if (player != null) {
                    playersLeft++;
                }
            }
            if (playersLeft == 1) {
                // Game is over, declare winner
                for (Player player : players) {
                    if (player != null) {
                        System.out.println(player.getName() + " is the winner!");
                        break;
                    }
                }
                gameOver = true;
            }

            System.out.println("\n" + players[(currentPlayerIndex + 1) % players.length].getName() + ", please enter 'roll' to roll the dice: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("roll")) {
                //  Move to the next player's turn
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            }
        }
    }

    /**
     * main entry point.  If args provided, result is displayed and program exists. Otherwise, user is prompted for
     * input.
     * @param args command line args.
     */

    public static void main(final String[] args) {
        logger.info("Application Started with args:{}", String.join(",", args));
        new Application();
        System.out.println("Hello. Welcome to Monopoly.");
        logger.info("Application closing");
    }
}