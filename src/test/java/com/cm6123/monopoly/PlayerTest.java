package com.cm6123.monopoly;

import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Facility;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.Property;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayer () {
        ArrayList<Player> players = new ArrayList<>();
        Player.addPlayers(players);
        assertEquals(3,players.size());
    }

    @Test
    public void createPlayer(){
        String[] playerNames = {"Alice", "Bob", "Charlie"};
        Player[] players = Player.createPlayers(3, playerNames);
        assertEquals(3, players.length);
        assertEquals("Alice", players[0].getName());
        assertEquals("Bob", players[1].getName());
        assertEquals("Charlie", players[2].getName());
    }

    @Test
    public void testMovePlayer() {
        Board board = new Board();
        Player player = new Player("John");
        int newPosition = player.movePlayer(4, board);
        assertEquals(5, newPosition);
    }
    @Test
    public void testDisplayFacility(){
        // Creating  an instance of the Player class with a player.
        Player player = new Player("Alice");

        // Initializing an ArrayList of Facility objects
        ArrayList<Facility> facilities = new ArrayList<>();
        facilities.add(new Facility("Swimming Pool", 8));
        facilities.add(new Facility("Movie Theater", 15));
        facilities.add(new Facility("Gym", 10));

        // inserting an assumed position of a player to see whether the facility the expected
        player.displayFacilities(facilities, 15);
        String facility = "Movie Theater";
        assertEquals(player.displayFacilities(facilities, 15),"Movie Theater");
    }

    @Test
    public void testDisplayProperties() {
        Player p = new Player("john");
        // Create a list of properties
        ArrayList<Property> properties = new ArrayList<>();
        properties.add(new Property("Boardwalk", 600,9));
        properties.add(new Property("Park Place", 900,11));
        properties.add(new Property("Marvin Gardens", 900,7));

        // inserting a temporary position to see whether it returns the correct name
        p.displayProperties(properties,11);
        String property = "Park Place";
        assertEquals(p.displayProperties(properties,11),"Park Place");

    }

    @Test
    public void testBalance(){
        Board b = new Board();
        Player p = new Player("Henry");
        p.movePlayer(10,b);
        assertEquals(11,p.getPosition());
        p.movePlayer(6,b);
        assertEquals(2,p.getPosition());
        assertEquals(1200,p.getBalance());
    }

    @Test
    public void testPurchaseProperty() {
        Player p= new Player("John");
        Property d = new Property("bus", 800, 1);
        Property m = new Property("market", 1200, 2);
        Property w = new Property("mall", 700, 3);
        p.purchaseProperty(d);
        assertEquals("John", d.getOwner());
        assertEquals(200, p.getBalance());

        Player a = new Player("Derrick");
        a.purchaseProperty(m);
        assertNull(null,m.getOwner());
        assertEquals(1000,a.getBalance());

        Player t = new Player("sharon");
        t.purchaseProperty(d);
        assertEquals("John",d.getOwner());
     }

     @Test
    public void testPayRent(){
         Property d = new Property("bus", 800, 1);
         Player j = new Player("James");
         j.purchaseProperty(d);
        Player w = new Player("White");
        w.payRent(d);
        assertEquals(920,w.getBalance());
     }

}

