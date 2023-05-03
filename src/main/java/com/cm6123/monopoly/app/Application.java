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


    private Application() {


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

