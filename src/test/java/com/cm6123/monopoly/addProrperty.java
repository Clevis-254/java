package com.cm6123.monopoly;


import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Property;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class addProrperty {

    @Test
    public void testproperty (){
        ArrayList<Property> properties = new ArrayList<>();
        Property.addProperty(properties);
        assertTrue(properties.size()==3);

    }

    @Test
    public void
    propertyname(){
        Board b = new Board();
        Property d = new Property("bus",600,1);
        Property m = new Property("market",800,2);
        Property w = new Property("mall",700,3);

        if (b.movePlayer() == 1){
            String expected ="You have reached the bus property. It costs $600.";
            String actual =d.propertyname();
            assertEquals(expected, actual);
            d.getName();
            d.getPrice();
        } else if (b.movePlayer() == 2) {
            String expected = "You have reached the Market property. It costs $800.";
            String actual = m.propertyname();
            assertEquals(expected, actual);
            m.getName();
            m.getPrice();
        } else if(b.movePlayer() == 3){
            String expected ="You have reached the Market property. It costs $800.";
            String actual = w.propertyname();
            assertEquals(expected, actual);
            w.getName();
            w.getPrice();
        } else {
            String expected = null;
            String actual = null;
            assertEquals(expected, actual);
        }
    }

}

