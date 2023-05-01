package com.cm6123.monopoly;


import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.Property;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.cm6123.monopoly.game.Property.addProperty;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyTest {

    @Test
    public void testproperty (){
        ArrayList<Property> properties = new ArrayList<>();
        addProperty(properties);
        assertTrue(properties.size()==3);

    }

    @Test
    public void
    propertyname(){
        Player p = new Player("John");
        Board b = new Board();
        int dice = p.rolledFigure(b);
        Property d = new Property("bus",600,1);
        Property m = new Property("market",800,2);
        Property w = new Property("mall",700,3);

        if (p.movePlayer(dice,b) == 1){
            String expected ="You have reached the bus property. It costs $600.";
            String actual =d.propertyname();
            assertEquals(expected, actual);
            d.getName();
            d.getPrice();
        } else if (p.movePlayer(dice,b) == 2) {
            String expected = "You have reached the market property. It costs $800.";
            String actual = m.propertyname();
            assertEquals(expected, actual);
            m.getName();
            m.getPrice();
        } else if(p.movePlayer(dice,b) == 3){
            String expected ="You have reached the mall property. It costs $700.";
            String actual = w.propertyname();
            assertEquals(expected, actual);
            w.getName();
            w.getPrice();
        }

    }

    @Test
    public void addPropertyTest() {
        ArrayList<Property> properties = new ArrayList<>();
        addProperty(properties);
        assertEquals(3, properties.size());
        assertEquals("mall", properties.get(0).getName());
        assertEquals(700, properties.get(0).getPrice());
        assertEquals(3, properties.get(0).getPosition());
        assertEquals("market", properties.get(1).getName());
        assertEquals(800, properties.get(1).getPrice());
        assertEquals(2, properties.get(1).getPosition());
        assertEquals("bus", properties.get(2).getName());
        assertEquals(600, properties.get(2).getPrice());
        assertEquals(1, properties.get(2).getPosition());
    }

}

