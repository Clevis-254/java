package com.cm6123.monopoly.app;
import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Facility;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        Scanner s = new Scanner(System.in);
        int numPlayers = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter the number of players playing: ");
                numPlayers = s.nextInt();
                if (numPlayers < 2 || numPlayers > 10) {
                    System.out.println("Number of players must be between 2 and 10.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 2 and 10.");
                s.next(); // consume the invalid input
            }
        }
        s.nextLine(); // consumes the newline character
        Player[] aplayers = createPlayersFromInput(numPlayers);

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
            Player currentPlayer = aplayers[currentPlayerIndex];

            // Roll the dice
            int diceRoll = currentPlayer.rolledFigure(board);
            System.out.println(currentPlayer.getName() + " has rolled a " + diceRoll);

            // Move the player
            int position = currentPlayer.movePlayer(diceRoll, board);
            if (currentPlayer.getPosition() == 1){
                System.out.println("You are now on home. you have received salary of £200. your new balance is "+ currentPlayer.getBalance());
            }
            // Display the location
            Property currentProperty = displayProperties(properties, position);
            Facility currentFacility = displayFacilities(facilities, position);

            if (currentProperty != null) {
                System.out.println("You have landed on "+currentProperty.getName());
                if (currentProperty.getOwner() != null) {
                    currentPlayer.payRent(currentProperty);
                    int rent = currentProperty.getRent();
                    Player owner = new Player(currentProperty.getOwner());
                    owner.getRent(rent);
                    System.out.println(currentPlayer.getName()+" has paid rent to the property owner, new balance is " + currentPlayer.getBalance());
                } else if (currentProperty.getOwner() == null) {
                    if (currentPlayer.getBalance() < currentProperty.getPrice()) {
                        System.out.println("Cannot purchase property as you have less balance");
                    } else {
                        System.out.println("Do you wish to purchase property? (yes/no)");
                        String answer = s.nextLine();
                        if (answer.equalsIgnoreCase("Yes")) {
                            currentPlayer.purchaseProperty(currentProperty);
                            System.out.println("congratulations on your purchase. new balance "+ currentPlayer.getBalance());
                        }
                    }
                }
            }

            if (currentFacility != null) {
                currentFacility.getName();
                System.out.println(currentPlayer.getName() +" has landed on "+ currentFacility.getName());
                if (currentFacility.getName().equals("Tax-office")) {
                    currentPlayer.payTax(board);
                    System.out.println(currentPlayer.getName() +" has paid taxes,new balance is " + currentPlayer.getBalance());
                } else if (currentFacility.getName().equals("Station")) {
                    currentPlayer.payFare(diceRoll);
                    System.out.println(currentPlayer.getName()+" has paid fare,new balance is " + currentPlayer.getBalance());
                }
            }
            // Handle bankruptcy
            if (currentPlayer.getBalance() == 0 && currentPlayer.countPropertyOwned() == 0) {
                // Remove player from the game
                System.out.println(currentPlayer.getName() + " is out of the game.");
                aplayers[currentPlayerIndex] = null;
            } else if (currentPlayer.getBalance() == 0 && currentPlayer.countPropertyOwned() > 0) {
                currentPlayer.handleBankruptcy(currentPlayer, properties);
            }
            // Check if game is over
            int playersLeft = 0;
            for (Player player : aplayers) {
                if (player != null) {
                    playersLeft++;
                }
            }
            if (playersLeft == 1) {
                // Game is over, declare winner
                for (Player player : aplayers) {
                    if (player != null) {
                        System.out.println(player.getName() + " is the winner!");
                        break;
                    }
                }
                gameOver = true;
            }

            System.out.println("\n" + aplayers[(currentPlayerIndex + 1) % aplayers.length].getName() + ", please enter 'roll' to roll the dice: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("roll")) {
                //  Move to the next player's turn
                currentPlayerIndex = (currentPlayerIndex + 1) % aplayers.length;
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
        System.out.println("Hello. Welcome to Monopoly.");
        new Application();
        logger.info("Application closing");
    }
}