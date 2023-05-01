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

    private  final Integer position;
    /**
     * constructor of the facilities class.
     * @param aname contains the name of the facility either name, railway, tax office.
     * @param aposition contains the position of the facilities.
     */
    public Facility(final String aname, final Integer aposition){
        this.name = aname;
        this.position = aposition;
    }
    /**
     * @return the name and position  of the facility
     * method that returns the name and position of facility if the player moves towards it.
     */

    public String facilityName(){
        Board b = new Board();
        b.movePlayer();
        if(b.movePlayer()== position){
            return name ;
        }
        return  name ;
    }

    /**
     * enters facilities into the board.
     * @param facilities represents the properties name.
     */

    public static void addFacilities(final ArrayList<Facility> facilities) {
        String[] names = {"road", "tax-office", "railway"};
        int[] positions = {4, 5, 6};

        for (int i = 0; i < names.length; i++) {
            Facility f = new Facility(names[i], positions[i]);
            facilities.add(f);
        }
    }


}
