package com.cm6123.monopoly.app;


import com.cm6123.monopoly.game.Facility;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Scanner;

import static com.cm6123.monopoly.game.Player.createPlayersFromInput;

public final class Application {
    /**
     * Create a logger for the class.
     */
    public static final  Logger logger = LoggerFactory.getLogger(Application.class);
    /**
     * name shows the number of players playing the game.
     */
    private int players;
    /**
     * names stores the name of players playing the game.
     */
    private final String[] names;

    private Application() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of players playing the game"); // the user enter the number of players who will be playing the game.
        players = s.nextInt();
        // the system checks whether the number of players is less than two or great than 10.
        while (players < 2 || players > 10) {
            System.out.println("Number of players must be between 2 and 10. Please enter again:");
            players = s.nextInt();
        }
        s.nextLine();// consumes the remaining line character.
        names = new String[players];
        // looping through the number of players in the game to get the name of the users.
        for (int i = 0; i < players; i++) {
            System.out.println("Enter the name of a player"+(i + 1));
            names[i] = s.nextLine();
        }
        for (int i = 0; i< names.length;i++){
            System.out.println("Player " + (i + 1) + " is " + names[i]); // display the name of the players and their number.
        }

        Player[] players = createPlayersFromInput();


    }

    /**
     * Method that displays the name of the property where the player has landed on.
     *
     * @param properties   The list of properties in the game.
     * @param newPosition  The new position of the player.
     * @return             The name of the property where the player has landed on.
     */
    private String displayProperties(final ArrayList<Property> properties, final int newPosition) {
        // Check if the player has landed on a property
        String propertyName = null;
        for (Property property : properties) {
            if (newPosition == property.getPosition()) {
                propertyName = property.getName();
                System.out.println("You have landed on " + property.getName());
            }
        }

        return propertyName;
    }

    /**
     * Method that displays the name of the facility where the player has landed on.
     *
     * @param facilities   The list of facilities in the game.
     * @param newPosition  The new position of the player.
     * @return             The name of the facility where the player has landed on.
     */
    private String displayFacilities(final ArrayList<Facility> facilities,final int newPosition) {
        // Check if the player has landed on a facility
        String facilityName = null;
        for (Facility facility : facilities) {
            if (newPosition == facility.getPosition()) {
                facilityName = facility.getName();
                System.out.println("You have landed on the " + facility.getName());
            }
        }

        return facilityName;
    }
        /**
         * main entry point.  If args provided, result is displayed and program exists. Otherwise, user is prompted for
         * input.
         *
         * @param args command line args.
         */
        public static void main (final String[] args){


            logger.info("Application Started with args:{}", String.join(",", args));

            System.out.println("Hello. Welcome to Monopoly.");
            Application a = new Application();
            new Application();
            logger.info("Application closing");
        }

    }

