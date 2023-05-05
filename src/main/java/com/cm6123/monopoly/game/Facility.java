package com.cm6123.monopoly.game;

import java.util.ArrayList;


public class Facility {

    /**
     * facilities names that shows the name of the facilities.
     */
    private final String name;
    /**
     * contains the name of the facilities.
     */

    private final Integer position;

    /**
     * constructor of the facilities class.
     *
     * @param aname     contains the name of the facility either name, railway, tax office.
     * @param aposition contains the position of the facilities.
     */
    public Facility(final String aname, final Integer aposition) {
        this.name = aname;
        this.position = aposition;
    }

    /**
     * method that gets the facility position.
     * @return the position of the facility.
     */
    public int getPosition() {
        return position;
    }

    /**
     * method that returns facility name.
     * @return name returns the name of facility.
     */
    public String getName() {
        return name;
    }

    /**
     * @param facilities     contains the facilities name.
     * @param playerPosition contains the players postion.
     * @return the name and position  of the facility
     * method that returns the name and position of facility if the player moves towards it.
     */

    public static String facilityName(final int playerPosition,final ArrayList<Facility> facilities) {
        for (Facility f : facilities) {
            if (f.getPosition() == playerPosition) {
                return f.getName();
            }
        }
        return null; // return null if the player is not on any facility
    }


    /**
     * enters facilities into the board.
     * @param facilities represents the properties name.
     */

    public static void addFacilities(final ArrayList<Facility> facilities) {
        String[] names = {"Road","Station","Tax-office"};
        int [] positions = {2,5,6};
        for (int i = 0; i < names.length; i++) { // use for loop to enter the date into the respective places in the board.
            Facility facility = new Facility(names[i],positions[i]);
            facilities.add(facility);
        }
    }

    /**
     * Method that displays the name of the facility where the player has landed on.
     *
     * @param facilities   The list of facilities in the game.
     * @param newPosition  The new position of the player.
     * @return The name of the facility where the player has landed on.
     */
    public static Facility displayFacilities(final ArrayList<Facility> facilities, final int newPosition) {
        // Check if the player has landed on a facility
        Facility facilitymatch = null;
        for (Facility facility : facilities) {
            if (newPosition == facility.getPosition()) {
                facilitymatch = facility;
            }
        }

        return facilitymatch;
    }
}
