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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

    @Test
    public void testMovePlayer() {
        Board board = new Board();
        Player player = new Player("John");
        int newPosition = player.movePlayer(4, board);
        assertEquals(5, newPosition);
    }

    @Test
    public void testBalance() {
        Board b = new Board();
        Player p = new Player("Henry");
        p.movePlayer(10, b);
        assertEquals(11, p.getPosition());
        p.movePlayer(10, b);
        assertEquals(1, p.getPosition());
        assertEquals(1200, p.getBalance());
    }

    @Test
    public void testPurchaseProperty() {
        Player p = new Player("John");
        Property d = new Property("bus", 800, 1);
        Property m = new Property("market", 1200, 2);
        Property w = new Property("mall", 700, 3);
        p.purchaseProperty(d);
        assertEquals("John", d.getOwner());
        assertEquals(200, p.getBalance());

        Player a = new Player("Derrick");
        a.purchaseProperty(m);
        assertNull(null, m.getOwner());
        assertEquals(1000, a.getBalance());

        Player t = new Player("sharon");
        t.purchaseProperty(d);
        assertEquals("John", d.getOwner());
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
        owner.getRent(80);

        // Check that the renter's balance has been updated
        assertEquals(920, renter.getBalance());

        // Check that the owner's balance has been updated
        assertEquals(280, owner.getBalance());
    }

    @Test
    public void testPayTax() {
        Board board = mock(Board.class);
        Player player = new Player("James");
        Player p = new Player("John");

        // Scenario 1: Rolled double
        when(board.rolledDouble()).thenReturn(true);
        player.payTax(board);
        assertEquals(950, player.getBalance());

        // Scenario 2: Did not roll double
        when(board.rolledDouble()).thenReturn(false);
        p.payTax(board);
        assertEquals(900, p.getBalance());
    }


    @Test
    public void testFare() {
        Player p = new Player("John");
        p.payFare(7);
        int expected = 930;
        int actual = p.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testHandleBankruptcy() {
        Player player = new Player("John");
        Player p = new Player ("James");
        Property e = new Property("Property 1", 600, 1);
        Property Q = new  Property("Property 2", 700, 2);
        Property u = new Property("Property 3", 800, 3);
        player.add(e);
        p.add(Q);
        p.add(u);

        // test case 2: player has only one property
        player.setBalance(0);
        assertEquals(1, player.countPropertyOwned());
        player.handleBankruptcy(player, player.getPropertiesOwned());
        assertEquals(300, player.getBalance());

        // test case 3: player has multiple properties
        p.setBalance(0);
        assertEquals(2, p.countPropertyOwned());
        p.handleBankruptcy(p, p.getPropertiesOwned());
        assertEquals(350, p.getBalance());
    }
}


