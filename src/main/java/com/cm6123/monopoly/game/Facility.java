package com.cm6123.monopoly.game;

import java.util.ArrayList;
import java.util.Scanner;

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
     * @returns the position of the facility.
     */
    public int getPosition() {
        return position;
    }

    /**
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

    public static String facilityName(int playerPosition, ArrayList<Facility> facilities) {
        for (Facility f : facilities) {
            if (f.getPosition() == playerPosition) {
                return f.getName();
            }
        }
        return null; // return null if the player is not on any facility
    }


    /**
     * enters facilities into the board.
     *
     * @param facilities represents the properties name.
     */

    public static void addFacilities(ArrayList<Facility> facilities) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter facility name (or 'done' to finish adding facilities): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter facility position: ");
            int position = scanner.nextInt();
            scanner.nextLine();

            Facility facility = new Facility(name, position);
            facilities.add(facility);
        }


    }
}
