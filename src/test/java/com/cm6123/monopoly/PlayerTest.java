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
    public void testMovePlayer() {
        Board board = new Board();
        Player player = new Player("John");
        int newPosition = player.movePlayer(4, board);
        assertEquals(5, newPosition);
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
    public void testRent() {
        // Create a property and a player who purchases it
        Property property = new Property("bus", 800, 1);
        Player owner = new Player("James");
        owner.purchaseProperty(property);

        // Check that the owner's balance has been updated
        assertEquals(200, owner.getBalance());

        // Create another player who rents the property
        Player renter = new Player("White");
        renter.payRent(property);

        // Check that the renter's balance has been updated
        assertEquals(920, renter.getBalance());

        // Check that the owner's balance has been updated
        //assertEquals(280, owner.getBalance());
    }

    @Test
    public void testTax(){
        Board b = new Board();
        Player p = new Player("James");
        p.movePlayer(5,b);
        Facility f = new Facility("Tax office",6);
        p.payTax(f,6);

        int expected = 900;
        int actual = p.getBalance();
        assertEquals(expected,actual);
    }
    @Test
    public void testFare(){
        Facility f = new Facility("Station",8);
        Player p = new Player("John");
        Board b = new Board();
        p.movePlayer(7,b);
        p.payFare(f,p,7);
        int expected = 930;
        int actual = p.getBalance();
        assertEquals(expected,actual);
    }

}

