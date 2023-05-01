package com.cm6123.monopoly.game;


import java.util.ArrayList;

public class Property {
    /**
     * the name of the property.
     */
    private final  String name;
    /**
     * the price of the property.
     */
    private final Integer price;
    /**
     * the place where the property is located.
     */
    private final  Integer position;
    /**
     * propert constructor.
     * @param aname  has the name of the property.
     * @param aprice has the price of the property.
     * @param aposition has the postion of the property.
     */
    public Property(final String aname,final Integer aprice,final Integer aposition){
        this.name = aname;
        this.price = aprice;
        this.position = aposition;
    }
    /**
     * method that returns property name.
     * @return  property name.
     */

    public String getName(){
        return name;
    }

    /**
     * method that return property price.
     * @return property price.
     */

    public Integer getPrice(){
        return price;
    }


    /**
     * enters properties into the board.
     * @param properties represents the properties name.
     */

    public static void addProperty(final ArrayList<Property> properties) {
        String[] names = {"mall", "market", "bus"};
        int[] prices = {700, 800, 600};
        int[] positions = {3, 2, 1};

        for (int i = 0; i < names.length; i++) { // use for loop to enter the date into the respective places in the board.
            Property property = new Property(names[i], prices[i], positions[i]);
            properties.add(property);
        }
    }


    /**
     * returns the name of the property when the move is at the property.
     *
     * @return name is the name of the property.
     */
    public String propertyname(){
        Board b = new Board();
        b.movePlayer();
        int playerPosition = b.movePlayer();
        if (playerPosition == position){
            return "You have reached the " + name + " property. It costs $" + price + ".";
        }
        return name + "positon";
    }
}


