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

    public static void addProperty(ArrayList<Property> properties){
        Property mall = new Property("mall",700,3);
        Property market = new Property("market",800,2);
        Property bus = new Property("bus",600,1);
        properties.add(mall);
        properties.add(market);
        properties.add(bus);
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
        if (playerPosition == position ){
            return "You have reached the " + name + " property. It costs $" + price + ".";
        }
        return name;
    }
}


