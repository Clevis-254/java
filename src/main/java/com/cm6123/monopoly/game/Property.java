package com.cm6123.monopoly.game;


import java.util.ArrayList;

public class Property {
    /**
     * the name of the property.
     */
    private final  String name;
    /**
     * owner of the property.
     */
    private String owner;
    /**
     * the price of the property.
     */
    private final Integer price;
    /**
     * the place where the property is located.
     */
    private final  Integer position;
    /**
     * the rent of the property.
     */
    private final Integer rent;
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
        this.owner = null;
        this.rent = 10 * price / 100;
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
     * @return position returns the position of property.
     * method that return the position of property.
     */

    public Integer getPosition(){
        return position;
    }
    /**
     * @return rent  returns the rent of a particular property.
     */
    public Integer getRent(){
        return rent;
    }
    /**
     * enters properties into the board.
     * @param properties represents the properties name.
     */

    public static void addProperty(final ArrayList<Property> properties) {
        String[] names = {"newtown-Apartment", "water-park", "roath-park","young-hotel","oldtown cinema","drive through mall","zoo world","cinema","Roath hotel","new park","old apartment"};
        int[] prices = {5500, 2500, 5000,6000,1500,1000,2500,1500,6000,3000,5500};
        int[] positions = {2, 4, 6,8,9,11,13,14,16,18,19};

        for (int i = 0; i < names.length; i++) { // use for loop to enter the date into the respective places in the board.
            Property property = new Property(names[i], prices[i], positions[i]);
            properties.add(property);
        }
    }


    /**
     * returns the name of the property when the move is at the property.
     * @param property  contains the name of property.
     * @return name is the name of the property.
     */
    public String propertyname(final Property property){
        return "You have reached the " + property.getName() + " property. It costs " + property.getPrice() + ".";
    }

    /**
     * method that returns the owner of the property.
     *
     * @return owner of the property.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * method that sets the owner of the property.
     * @param aowner the name of the player who bought the property.
     * @return new owner.
     */
    public String setOwner(final String aowner) {
        this.owner = aowner;
        return aowner;
    }
    /**
     * Method that displays the name of the property where the player has landed on.
     * @param properties  The list of properties in the game.
     * @param newPosition The new position of the player.
     * @return The name of the property where the player has landed on.
     */
    public static Property displayProperties(final ArrayList<Property> properties, final int newPosition) {
        // Check if the player has landed on a property
        Property propertyMatch = null;
        for (Property property : properties) {
            if (newPosition == property.getPosition()) {
                propertyMatch = property;
            }
        }

        return propertyMatch;
    }

}


