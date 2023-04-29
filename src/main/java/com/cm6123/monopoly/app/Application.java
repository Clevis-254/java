package com.cm6123.monopoly.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public final class Application {
    /**
     * Create a logger for the class.
     */
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    /**
     * name shows the number of players playing the game.
     */
    private int players;
    /**
     * names stores the name of players playing the game.
     */
    private String[] names;

    private Application() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of players playing the game"); // the user enter the number of players who will be playing the game.
        players = s.nextInt();
        // the system checks whether the number of players is less than two or great than 10.
        while (players < 2 || players > 10) {
            System.out.println("Number of players must be between 2 and 10. Please enter again:");
            players = s.nextInt();
        }

        names = new String[players];
        // looping through the number of players in the game to get the name of the users.
        for (int i = 0; i < players; i++) {
            System.out.println("Enter the name of a player");
            names[i] = s.nextLine();
        }
        for (int i = 0; i< names.length;i++){
            System.out.println("Player " + (i + 1) + " is " + names[i]); // display the name of the players and their number.
        }
    }

    /**
     * main entry point.  If args provided, result is displayed and program exists. Otherwise, user is prompted for
     * input.
     *
     * @param args command line args.
     */
    public static void main(final String[] args) {


        logger.info("Application Started with args:{}", String.join(",", args));

        System.out.println("Hello. Welcome to Monopoly.");
        Application a = new Application();
        new Application();
        logger.info("Application closing");
    }


}
