package com.cm6123.monopoly.app;

import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public final class Application {
    /**
     * Create a logger for the class.
     */
    private static final  Logger logger = LoggerFactory.getLogger(Application.class);
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

        Player[] players = Player.createPlayers(this.players, this.names);
        for (int i = 0; i < players.length; i++) {
            logger.info (players[i].getName() + " has been added to the game."); // displays the name of the player in the system.
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
