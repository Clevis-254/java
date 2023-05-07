package com.cm6123.monopoly;


import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.Property;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.cm6123.monopoly.game.Property.addProperty;
import static com.cm6123.monopoly.game.Property.displayProperties;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    @Test
    public void testproperty (){
        ArrayList<Property> properties = new ArrayList<>();
        addProperty(properties);
        assertEquals(11,properties.size());

    }

    @Test
    public void
    testPropertyname(){
        Player p = new Player("John");
        Board b = new Board();
        int dice = p.rolledFigure(b);
        Property d = new Property("bus",600,9);
        Property m = new Property("market",800,7);
        Property w = new Property("mall",700,3);

        if (p.movePlayer(dice,b) == 9){
            String expected ="You have reached the bus property. It costs 600.";
            String actual =d.propertyname(d);
            assertEquals(expected, actual);
        } else if (p.movePlayer(dice,b) == 7) {
            String expected = "You have reached the market property. It costs 800.";
            String actual = m.propertyname(m);
            assertEquals(expected, actual);
        } else if(p.movePlayer(dice,b) == 3){
            String expected ="You have reached the mall property. It costs 700.";
            String actual = w.propertyname(w);
            assertEquals(expected, actual);
        }

    }

    @Test
    public void addPropertyTest() {
        ArrayList<Property> properties = new ArrayList<>();
        addProperty(properties);
        assertEquals("newtown-Apartment", properties.get(0).getName());
        assertEquals(5500, properties.get(0).getPrice());
        assertEquals(2, properties.get(0).getPosition());
        assertEquals("water-park", properties.get(1).getName());
        assertEquals(2500, properties.get(1).getPrice());
        assertEquals(4, properties.get(1).getPosition());
        assertEquals("roath-park", properties.get(2).getName());
        assertEquals(5000, properties.get(2).getPrice());
        assertEquals(6, properties.get(2).getPosition());
    }

    @Test
    public void testRent(){
        Property d = new Property("bus",600,1);
        d.getRent();
        assertEquals(60,d.getRent());
    }

    @Test
    public void testDisplayProperties() {
        ArrayList<Property> properties = new ArrayList<>();
        properties.add(new Property("Park Lane", 200, 2));
        properties.add(new Property("Mayfair", 400, 4));

        Property property1 = displayProperties(properties, 2);
        assertNotNull(property1);
        assertEquals("Park Lane", property1.getName());

        Property property2 = displayProperties(properties, 4);
        assertNotNull(property2);
        assertEquals("Mayfair", property2.getName());

        Property property3 = displayProperties(properties, 3);
        assertNull(property3);
    }

}

